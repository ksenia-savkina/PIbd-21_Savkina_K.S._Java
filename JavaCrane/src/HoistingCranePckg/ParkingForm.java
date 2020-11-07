package HoistingCranePckg;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Stack;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;

public class ParkingForm {

	private JFrame frame;
	private PanelParking panel;
	private JTextField txtFldPlace;
	private JTextField txtCount;
	private JLabel label;
	private Integer k;
	private JTextField txtFldNumber;
	private JButton btnMore;
	private JTextField txtNewLevelName;
	private DefaultListModel<String> listParkingsModel;
	private JList<String> listParkings;
	private final ParkingCollection parkingCollection;
	private final Stack<Platform> platformStack;

	public ParkingForm() {
		
		platformStack = new Stack<>();
		
		frame = new JFrame("Стоянка");
		frame.setBounds(50, 50, 1330, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		txtCount = new JTextField();
		txtCount.setEditable(false);
		txtCount.setBounds(1162, 437, 100, 20);
		frame.getContentPane().add(txtCount);
		txtCount.setColumns(10);

		label = new JLabel();
		label.setBounds(1181, 388, 71, 50);
		frame.getContentPane().add(label);

		panel = new PanelParking(false, false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 1086, 713);
		frame.getContentPane().add(panel);
		
		parkingCollection = new ParkingCollection(panel.getWidth(), panel.getHeight());
		panel.setParkingCollection(parkingCollection);
		
		listParkingsModel = new DefaultListModel();
		listParkings = new JList(listParkingsModel);
		listParkings.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                panel.setSelectedItem(listParkings.getSelectedValue());
                panel.repaint();
			}
		});
		listParkings.setBounds(1152, 125, 110, 59);
		frame.getContentPane().add(listParkings);

		JButton btnSetTrackedVehicle = new JButton("<html>Припарковать гусеничную машину</html>");
		btnSetTrackedVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listParkings.getSelectedIndex() > -1) {
					JColorChooser dialog = new JColorChooser();
					JOptionPane.showMessageDialog(frame, dialog);
					if (dialog.getColor() != null) {
						TrackedVehicle crane = new TrackedVehicle(100, 1000, dialog.getColor());
						if (parkingCollection.get(listParkings.getSelectedValue()).addition(crane)) {
							panel.setCrane(crane);
							panel.repaint();
						} else {
							JOptionPane.showMessageDialog(frame, "Стоянка переполнена");
						}
					}
				}
			}
		});
		btnSetTrackedVehicle.setBounds(1148, 256, 145, 47);
		frame.getContentPane().add(btnSetTrackedVehicle);

		JButton btnSetHoistingCrane = new JButton("<html>Припарковать подъемный кран</html>");
		btnSetHoistingCrane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listParkings.getSelectedIndex() > -1) {
					k = rnd(4, 6);
					JColorChooser dialog = new JColorChooser();
					JOptionPane.showMessageDialog(frame, dialog);
					if (dialog.getColor() != null) {
						JColorChooser dialogDop = new JColorChooser();
						JOptionPane.showMessageDialog(frame, dialogDop);
						if (dialogDop.getColor() != null) {
							TrackedVehicle crane = new HoistingCrane(100, 1000, dialog.getColor(), dialogDop.getColor(),
									true, true, k, 0);
							if (parkingCollection.get(listParkings.getSelectedValue()).addition(crane)) {
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
			}
		});
		btnSetHoistingCrane.setBounds(1148, 331, 145, 47);
		frame.getContentPane().add(btnSetHoistingCrane);

		JLabel lblTakeCrane = new JLabel("Забрать кран");
		lblTakeCrane.setBounds(1180, 479, 81, 30);
		frame.getContentPane().add(lblTakeCrane);

		JLabel lblPlace = new JLabel("Место: ");
		lblPlace.setBounds(1159, 513, 52, 30);
		frame.getContentPane().add(lblPlace);

		txtFldPlace = new JTextField();
		txtFldPlace.setBounds(1235, 519, 58, 19);
		frame.getContentPane().add(txtFldPlace);
		txtFldPlace.setColumns(10);

		JLabel lblCompare = new JLabel("<html>Количество занятых мест меньше или больше введеного числа?</html>");
		lblCompare.setBounds(1124, 617, 192, 50);
		frame.getContentPane().add(lblCompare);

		txtFldNumber = new JTextField();
		txtFldNumber.setColumns(10);
		txtFldNumber.setBounds(1124, 689, 58, 19);
		frame.getContentPane().add(txtFldNumber);

		btnMore = new JButton(">");
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (parkingCollection.get(listParkings.getSelectedValue())
						.more(Integer.valueOf(txtFldNumber.getText()))) {
					JOptionPane.showMessageDialog(frame, "Верно! Количество занятых мест больше введеного числа");
				} else {
					JOptionPane.showMessageDialog(frame, "Неверно!");
				}
			}
		});
		btnMore.setBounds(1193, 677, 85, 21);
		frame.getContentPane().add(btnMore);

		JButton btnLess = new JButton("<");
		btnLess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (parkingCollection.get(listParkings.getSelectedValue())
						.less(Integer.valueOf(txtFldNumber.getText()))) {
					JOptionPane.showMessageDialog(frame, "Верно! Количество занятых мест меньше введеного числа");
				} else {
					JOptionPane.showMessageDialog(frame, "Неверно!");
				}
			}
		});
		btnLess.setBounds(1193, 702, 85, 21);
		frame.getContentPane().add(btnLess);

		JLabel lblNewLevelName = new JLabel("Стоянки:");
		lblNewLevelName.setBounds(1181, 23, 58, 20);
		frame.getContentPane().add(lblNewLevelName);

		txtNewLevelName = new JTextField();
		txtNewLevelName.setColumns(10);
		txtNewLevelName.setBounds(1152, 53, 100, 20);
		frame.getContentPane().add(txtNewLevelName);

		JButton btnAddParking = new JButton("Добавить стоянку");
		btnAddParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtNewLevelName.getText().equals("")) {
					parkingCollection.addParking(txtNewLevelName.getText());
					reloadLevels();
					panel.repaint();
				} else {
					JOptionPane.showMessageDialog(frame, "Введите название стоянки", "Ошибка",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddParking.setBounds(1142, 83, 151, 20);
		frame.getContentPane().add(btnAddParking);

		JButton btnDelParking = new JButton("Удалить стоянку");
		btnDelParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listParkings.getSelectedIndex() > -1) {
					int result = JOptionPane.showConfirmDialog(frame,
							"Удалить стоянку " + listParkings.getSelectedIndex() + "?", "Удаление",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						parkingCollection.delParking(listParkings.getSelectedValue());
						reloadLevels();
						panel.repaint();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Гараж не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnDelParking.setBounds(1148, 206, 136, 20);
		frame.getContentPane().add(btnDelParking);
		
		JButton btnAddToStack = new JButton("<html>Добавить в стек</html>");
		btnAddToStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listParkings.getSelectedIndex() > -1 && txtFldPlace.getText() != "") {
					Platform crane = parkingCollection.get(listParkings.getSelectedValue())
							.subtraction(Integer.valueOf(txtFldPlace.getText()));
					if (crane != null) {
						platformStack.add(crane);
						frame.repaint();
					}
					panel.repaint();
				}
			}
		});
		btnAddToStack.setBounds(1106, 554, 101, 47);
		frame.getContentPane().add(btnAddToStack);
		
		JButton btnRemoveFromStack = new JButton("<html>Извлечь из стека</html>");
		btnRemoveFromStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!platformStack.isEmpty()) {
					CraneForm form = new CraneForm();
					form.setCrane(Objects.requireNonNull(platformStack.pop()));
					panel.repaint();
		            frame.repaint();
		        }
			}
		});
		btnRemoveFromStack.setBounds(1217, 554, 101, 47);
		frame.getContentPane().add(btnRemoveFromStack);
		frame.repaint();
	}

	private int rnd(int min, int max) {
		max -= min;
		return (int) (Math.random() * ++max) + min;
	}

	private void reloadLevels() {
		int index = listParkings.getSelectedIndex();
		listParkingsModel.removeAllElements();
		int i = 0;
		for (String name : parkingCollection.keySet()) {
			listParkingsModel.add(i, name);
			i++;
		}
		if (listParkingsModel.size() > 0 && (index == -1 || index >= listParkingsModel.size())) {
			listParkings.setSelectedIndex(0);
		} else if (listParkingsModel.size() > 0 && index > -1 && index < listParkingsModel.size()) {
			listParkings.setSelectedIndex(index);
		}
		panel.repaint();
	}
}
