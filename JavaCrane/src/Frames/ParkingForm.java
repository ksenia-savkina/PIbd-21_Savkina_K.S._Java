package Frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import Exceptions.CraneNotFoundException;
import Exceptions.ParkingOverflowException;

import javax.swing.JList;
import javax.swing.JMenu;

import HoistingCranePckg.ParkingCollection;
import HoistingCranePckg.Platform;

import Panels.PanelParking;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	private static Logger logger;

	public ParkingForm() {

		logger = Logger.getLogger(ParkingForm.class);
		PropertyConfigurator.configure("log4j.properties");

		platformStack = new Stack<>();

		frame = new JFrame("Стоянка");
		frame.setBounds(90, 20, 1330, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		panel = new PanelParking(false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 42, 1086, 711);
		frame.getContentPane().add(panel);

		parkingCollection = new ParkingCollection(panel.getWidth(), panel.getHeight());
		panel.setParkingCollection(parkingCollection);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					FileReader fileReader = new FileReader("C:\\alteralt7\\log.log");
					Scanner scanner = new Scanner(fileReader);
					StringBuilder mailText = new StringBuilder();
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						if (line.contains("FATAL")) {
							mailText.append(line + "\n");
						}
					}
					if (!mailText.toString().isEmpty()) {
						logger.fatal(mailText.toString());
					}
				} catch (Exception ex) {
					ex.printStackTrace();

				}
			}
		});

		listParkingsModel = new DefaultListModel();
		listParkings = new JList(listParkingsModel);
		listParkings.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				panel.setSelectedItem(listParkings.getSelectedValue());
				logger.info("Перешли на стоянку " + listParkings.getSelectedValue());
				panel.repaint();
			}
		});
		listParkings.setBounds(1152, 125, 110, 89);
		frame.getContentPane().add(listParkings);

		JButton btnSetCrane = new JButton("Добавить кран");
		btnSetCrane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openCraneConfigFrame();
			}
		});
		btnSetCrane.setBounds(1138, 266, 153, 30);
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
					logger.info("Добавили стоянку " + txtNewLevelName.getText());
					reloadLevels();
					panel.repaint();
				} else {
					logger.warn("Название стоянки не введено");
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
						logger.info("Удалили стоянку " + listParkings.getSelectedIndex());
						reloadLevels();
						panel.repaint();
					}
				} else {
					logger.warn("Стоянка не выбрана");
					JOptionPane.showMessageDialog(frame, "Стоянка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnDelParking.setBounds(1140, 236, 136, 20);
		frame.getContentPane().add(btnDelParking);

		JButton btnAddToStack = new JButton("<html>Добавить в стек</html>");
		btnAddToStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listParkings.getSelectedIndex() > -1) {
					if (txtFldPlace.getText() != "") {
						try {
							Platform crane = parkingCollection.get(listParkings.getSelectedValue())
									.subtraction(Integer.valueOf(txtFldPlace.getText()));
							logger.info("Добавлен кран " + crane.toString());
							platformStack.add(crane);
							panel.repaint();
						} catch (CraneNotFoundException ex) {
							logger.error("Кран не найден");
							JOptionPane.showMessageDialog(frame, "Кран не найден");
						} catch (Exception e) {
							logger.fatal("Неизвестная ошибка");
							JOptionPane.showMessageDialog(frame, "Крана с таким индексом нет!", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						logger.warn("Индекс не введен");
						JOptionPane.showMessageDialog(frame, "Индекс не введен");
					}
				} else {
					logger.warn("Стоянка не выбрана");
					JOptionPane.showMessageDialog(frame, "Стоянка не выбрана");
				}
			}
		});
		btnAddToStack.setBounds(1104, 381, 101, 47);
		frame.getContentPane().add(btnAddToStack);

		JButton btnRemoveFromStack = new JButton("<html>Извлечь из стека</html>");
		btnRemoveFromStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!platformStack.isEmpty()) {
					logger.info("Кран извлечен из стека " + platformStack.peek().toString());
					CraneForm form = new CraneForm();
					form.setCrane(Objects.requireNonNull(platformStack.pop()));
					panel.repaint();
				} else {
					logger.warn("Стек пуст");
					JOptionPane.showMessageDialog(frame, "Стек пуст");
				}
			}
		});
		btnRemoveFromStack.setBounds(1215, 381, 101, 47);
		frame.getContentPane().add(btnRemoveFromStack);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 10, 1086, 22);
		frame.getContentPane().add(menuBar);

		JMenu fileMenu = new JMenu("Файл");
		menuBar.add(fileMenu);

		JMenuItem menuItemSave = new JMenuItem("Сохранить");
		menuItemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showDialog(frame, "Сохраннить парковки");
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						String filename = fileChooser.getSelectedFile().toString();
						if (filename.contains(".txt")) {
							parkingCollection.saveData(filename);
						} else {
							parkingCollection.saveData(filename + ".txt");
						}
						JOptionPane.showMessageDialog(frame, "Сохранение прошло успешно");
						logger.info("Сохранено в файл " + filename);
					} catch (Exception e) {
						logger.fatal("Ошибка при сохранении");
						JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при сохранении");
					}
				} else {
					logger.warn("Не удалось сохранить файл");
					JOptionPane.showMessageDialog(frame, "Не удалось сохранить файл");
				}
			}
		});
		fileMenu.add(menuItemSave);

		JMenuItem menuItemLoad = new JMenuItem("Загрузить");
		menuItemLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						String filename = fileChooser.getSelectedFile().toString();
						parkingCollection.loadData(filename);
						logger.info("Загружено из файла " + filename);
						reloadLevels();
						frame.repaint();
					} catch (Exception e) {
						logger.fatal("Ошибка при загрузке");
						JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при загрузке");
					}
				} else {
					logger.warn("Не удалось загрузить файл");
					JOptionPane.showMessageDialog(frame, "Не удалось загрузить файл");
				}
			}
		});
		fileMenu.add(menuItemLoad);

		JMenuItem menuItemSaveSeparateParking = new JMenuItem("Сохранить текущую парковку");
		menuItemSaveSeparateParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showDialog(frame, "Сохраннить парковку");
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						String filename = fileChooser.getSelectedFile().toString();
						if (filename.contains(".txt")) {
							parkingCollection.saveSeparateParking(filename, listParkings.getSelectedValue());
						} else {
							parkingCollection.saveSeparateParking(filename + ".txt", listParkings.getSelectedValue());
						}
						JOptionPane.showMessageDialog(frame, "Сохранение прошло успешно");
						logger.info("Текущая парковка сохранена в файл " + filename);
					} catch (Exception e) {
						logger.fatal("Ошибка при сохранении текущей парковки");
						JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при сохранении");
					}
				} else {
					logger.warn("Не удалось сохранить файл с текущей парковкой");
					JOptionPane.showMessageDialog(frame, "Не удалось сохранить файл");
				}
			}
		});
		fileMenu.add(menuItemSaveSeparateParking);

		JMenuItem menuItemLoadSeparateParking = new JMenuItem("Загрузить отдельную парковку");
		menuItemLoadSeparateParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						String filename = fileChooser.getSelectedFile().toString();
						parkingCollection.loadSeparateParking(filename);
						logger.info("Отдельная парковка загружена из файла " + filename);
						reloadLevels();
						frame.repaint();
					} catch (Exception e) {
						logger.fatal("Ошибка при загрузке отдельной парковки");
						JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при загрузке");
					}
				} else {
					logger.warn("Не удалось загрузить файл с отдельной парковкой");
					JOptionPane.showMessageDialog(frame, "Не удалось загрузить файл");
				}
			}
		});
		fileMenu.add(menuItemLoadSeparateParking);

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
			try {
				if (parkingCollection.get(listParkings.getSelectedValue()).addition(crane)) {
					logger.info("Добавлен кран " + crane.toString());
					panel.repaint();
				} else {
					logger.info("Кран не удалось поставить");
					JOptionPane.showMessageDialog(frame, "Кран не удалось поставить");
				}
			} catch (ParkingOverflowException ex) {
				logger.error("Переполнение");
				JOptionPane.showMessageDialog(frame, "Стоянка переполнена");
			} catch (Exception ex) {
				logger.fatal("Неизвестная ошибка");
				JOptionPane.showMessageDialog(frame, "Неизвестная ошибка");
			}
		} else {
			logger.warn("Кран не может быть добавлен");
			JOptionPane.showMessageDialog(frame, "Кран не может быть добавлен");
		}
	}

	private void openCraneConfigFrame() {
		CraneConfigForm formCraneConfig = new CraneConfigForm(this);
	}
}
