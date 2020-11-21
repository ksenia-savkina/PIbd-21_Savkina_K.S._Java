package Frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Objects;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;

import HoistingCranePckg.ParkingCollection;
import HoistingCranePckg.Platform;

import Panels.PanelParking;

public class ParkingForm {

	private JFrame frame;
	private PanelParking panel;
	private JTextField txtFldPlace;
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

		panel = new PanelParking(false);
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

		JButton btnSetCrane = new JButton("Добавить кран");
		btnSetCrane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openCraneConfigFrame();
			}
		});
		btnSetCrane.setBounds(1140, 246, 153, 30);
		frame.getContentPane().add(btnSetCrane);

		JLabel lblTakeCrane = new JLabel("Забрать кран");
		lblTakeCrane.setBounds(1178, 306, 81, 30);
		frame.getContentPane().add(lblTakeCrane);

		JLabel lblPlace = new JLabel("Место: ");
		lblPlace.setBounds(1157, 340, 52, 30);
		frame.getContentPane().add(lblPlace);

		txtFldPlace = new JTextField();
		txtFldPlace.setBounds(1233, 346, 58, 19);
		frame.getContentPane().add(txtFldPlace);
		txtFldPlace.setColumns(10);

		JLabel lblCompare = new JLabel("<html>Количество занятых мест меньше или больше введеного числа?</html>");
		lblCompare.setBounds(1122, 444, 192, 50);
		frame.getContentPane().add(lblCompare);

		txtFldNumber = new JTextField();
		txtFldNumber.setColumns(10);
		txtFldNumber.setBounds(1122, 516, 58, 19);
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
		btnMore.setBounds(1191, 504, 85, 21);
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
		btnLess.setBounds(1191, 529, 85, 21);
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
					}
					panel.repaint();
				}
			}
		});
		btnAddToStack.setBounds(1104, 381, 101, 47);
		frame.getContentPane().add(btnAddToStack);

		JButton btnRemoveFromStack = new JButton("<html>Извлечь из стека</html>");
		btnRemoveFromStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!platformStack.isEmpty()) {
					CraneForm form = new CraneForm();
					form.setCrane(Objects.requireNonNull(platformStack.pop()));
					panel.repaint();
				}
			}
		});
		btnRemoveFromStack.setBounds(1215, 381, 101, 47);
		frame.getContentPane().add(btnRemoveFromStack);

		frame.repaint();
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

	public void addCrane(Platform crane) {
		if (crane != null && listParkings.getSelectedIndex() > -1) {
			if (parkingCollection.get(listParkings.getSelectedValue()).addition(crane)) {
				panel.repaint();
			} else {
				JOptionPane.showMessageDialog(frame, "Кран не удалось поставить");
			}
		}
	}

	private void openCraneConfigFrame() {
		CraneConfigForm formCraneConfig = new CraneConfigForm(this);
	}
}