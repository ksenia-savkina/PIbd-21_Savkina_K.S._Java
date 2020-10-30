package HoistingCranePckg;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ParkingForm {

	private JFrame frame;
	private final Parking<TrackedVehicle, Rink> parking;
	private PanelParking panel;
	private JTextField txtFldPlace;
	private JTextField txtCount;
	private JLabel label;
	private Integer k;
	private JTextField txtFldNumber;
	private JButton btnMore;

	public ParkingForm() {
		frame = new JFrame("Стоянка");
		frame.setBounds(50, 50, 1330, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		txtCount = new JTextField();
		txtCount.setEditable(false);
		txtCount.setBounds(1175, 203, 100, 20);
		frame.getContentPane().add(txtCount);
		txtCount.setColumns(10);

		label = new JLabel();
		label.setBounds(1194, 154, 71, 50);
		frame.getContentPane().add(label);

		panel = new PanelParking(false, false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 1086, 713);
		frame.getContentPane().add(panel);

		parking = new Parking<TrackedVehicle, Rink>(panel.getWidth(), panel.getHeight());
		panel.setParking(parking);

		JButton btnSetTrackedVehicle = new JButton("<html>Припарковать гусеничную машину</html>");
		btnSetTrackedVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JColorChooser dialog = new JColorChooser();
				JOptionPane.showMessageDialog(frame, dialog);
				if (dialog.getColor() != null) {
					TrackedVehicle crane = new TrackedVehicle(100, 1000, dialog.getColor());
					if (parking.addition(crane)) {
						panel.setCrane(crane);
						panel.repaint();
					} else {
						JOptionPane.showMessageDialog(frame, "Стоянка переполнена");
					}
				}
			}
		});
		btnSetTrackedVehicle.setBounds(1161, 22, 145, 47);
		frame.getContentPane().add(btnSetTrackedVehicle);

		JButton btnSetHoistingCrane = new JButton("<html>Припарковать подъемный кран</html>");
		btnSetHoistingCrane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				k = rnd(4, 6);
				JColorChooser dialog = new JColorChooser();
				JOptionPane.showMessageDialog(frame, dialog);
				if (dialog.getColor() != null) {
					JColorChooser dialogDop = new JColorChooser();
					JOptionPane.showMessageDialog(frame, dialogDop);
					if (dialogDop.getColor() != null) {
						TrackedVehicle crane = new HoistingCrane(100, 1000, dialog.getColor(), dialogDop.getColor(),
								true, true, k, 0);
						if (parking.addition(crane)) {
							label.setText("<html>Количетсво катков в гусеницах:</html>");
							txtCount.setText(k.toString());
							panel.setCrane(crane);
							panel.repaint();
						} else {
							JOptionPane.showMessageDialog(frame, "Стоянка переполнена");
						}
					}
				}
			}
		});
		btnSetHoistingCrane.setBounds(1161, 97, 145, 47);
		frame.getContentPane().add(btnSetHoistingCrane);

		JLabel lblTakeCrane = new JLabel("Забрать кран");
		lblTakeCrane.setBounds(1193, 245, 81, 30);
		frame.getContentPane().add(lblTakeCrane);

		JLabel lblPlace = new JLabel("Место: ");
		lblPlace.setBounds(1172, 279, 52, 30);
		frame.getContentPane().add(lblPlace);

		txtFldPlace = new JTextField();
		txtFldPlace.setBounds(1248, 285, 58, 19);
		frame.getContentPane().add(txtFldPlace);
		txtFldPlace.setColumns(10);

		JButton btnTakeCrane = new JButton("Забрать");
		btnTakeCrane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtFldPlace.getText() != "") {
					TrackedVehicle crane = parking.subtraction(Integer.valueOf(txtFldPlace.getText()));
					if (crane != null) {
						CraneForm form = new CraneForm();
						form.setCrane(crane);
					}
					panel.repaint();
				}
			}
		});
		btnTakeCrane.setBounds(1174, 331, 101, 30);
		frame.getContentPane().add(btnTakeCrane);

		JLabel lblCompare = new JLabel("<html>Количество занятых мест меньше или больше введеного числа?</html>");
		lblCompare.setBounds(1114, 381, 192, 50);
		frame.getContentPane().add(lblCompare);

		txtFldNumber = new JTextField();
		txtFldNumber.setColumns(10);
		txtFldNumber.setBounds(1124, 454, 58, 19);
		frame.getContentPane().add(txtFldNumber);

		btnMore = new JButton(">");
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (parking.more(Integer.valueOf(txtFldNumber.getText()))) {
					JOptionPane.showMessageDialog(frame, "Верно! Количество занятых мест больше введеного числа");
				} else {
					JOptionPane.showMessageDialog(frame, "Неверно!");
				}
			}
		});
		btnMore.setBounds(1193, 442, 85, 21);
		frame.getContentPane().add(btnMore);

		JButton btnLess = new JButton("<");
		btnLess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (parking.less(Integer.valueOf(txtFldNumber.getText()))) {
					JOptionPane.showMessageDialog(frame, "Верно! Количество занятых мест меньше введеного числа");
				} else {
					JOptionPane.showMessageDialog(frame, "Неверно!");
				}
			}
		});
		btnLess.setBounds(1193, 467, 85, 21);
		frame.getContentPane().add(btnLess);
		frame.repaint();
	}

	private int rnd(int min, int max) {
		max -= min;
		return (int) (Math.random() * ++max) + min;
	}
}
