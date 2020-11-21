package Frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.TransferHandler;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import HoistingCranePckg.CircleRink;
import HoistingCranePckg.DragMouseAdapter;
import HoistingCranePckg.HoistingCrane;
import HoistingCranePckg.Ornament1Rink;
import HoistingCranePckg.Ornament2Rink;
import HoistingCranePckg.Platform;
import HoistingCranePckg.TrackedVehicle;

import Panels.PanelCraneConfig;

public class CraneConfigForm {

	private JFrame frame;
	private PanelCraneConfig panel;
	// Переменная-выбранный кран
	Platform crane = null;
	ParkingForm parkingForm;
	private JSpinner spinnerMaxSpeed;
	private JSpinner spinnerWeight;

	public CraneConfigForm(ParkingForm parkingForm) {
		this.parkingForm = parkingForm;

		frame = new JFrame("Подъемный кран");
		frame.setBackground(new Color(240, 240, 240));
		frame.setTitle("Выбор крана");
		frame.setBounds(100, 100, 1000, 570);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);

		panel = new PanelCraneConfig(false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(334, 28, 366, 365);
		frame.getContentPane().add(panel);

		JLabel lblAssembly = new JLabel("Тип сборки");
		lblAssembly.setBounds(38, 34, 76, 13);
		frame.getContentPane().add(lblAssembly);

		JLabel lblTrackedVehicle = new JLabel("Гусеничная машина");
		lblTrackedVehicle.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTrackedVehicle.setBounds(36, 57, 130, 26);
		frame.getContentPane().add(lblTrackedVehicle);

		JLabel lblHoistingCrane = new JLabel("Подъемный кран");
		lblHoistingCrane.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHoistingCrane.setBounds(38, 104, 119, 26);
		frame.getContentPane().add(lblHoistingCrane);

		JLabel lblOptions = new JLabel("Параметры");
		lblOptions.setBounds(38, 172, 76, 13);
		frame.getContentPane().add(lblOptions);

		JLabel lblNewLabel = new JLabel("Максимальная скорость");
		lblNewLabel.setBounds(38, 205, 119, 13);
		frame.getContentPane().add(lblNewLabel);

		spinnerMaxSpeed = new JSpinner();
		spinnerMaxSpeed.setModel(new SpinnerNumberModel(100, 100, 1000, 1));
		spinnerMaxSpeed.setBounds(166, 202, 44, 20);
		frame.getContentPane().add(spinnerMaxSpeed);

		JLabel lblWeight = new JLabel("Вес крана");
		lblWeight.setBounds(38, 247, 119, 13);
		frame.getContentPane().add(lblWeight);

		spinnerWeight = new JSpinner();
		spinnerWeight.setModel(new SpinnerNumberModel(100, 100, 1000, 1));
		spinnerWeight.setBounds(166, 244, 44, 20);
		frame.getContentPane().add(spinnerWeight);

		JCheckBox chckbxArrow = new JCheckBox("Стрела");
		chckbxArrow.setBounds(235, 201, 93, 21);
		frame.getContentPane().add(chckbxArrow);

		JCheckBox chckbxCounterweight = new JCheckBox("Противовес");
		chckbxCounterweight.setBounds(235, 243, 93, 21);
		frame.getContentPane().add(chckbxCounterweight);

		JPanel panelBlue = new JPanel();
		panelBlue.setBackground(Color.BLUE);
		panelBlue.setBounds(770, 132, 23, 20);
		frame.getContentPane().add(panelBlue);

		JPanel panelPink = new JPanel();
		panelPink.setBackground(Color.PINK);
		panelPink.setBounds(815, 132, 23, 20);
		frame.getContentPane().add(panelPink);

		JPanel panelOrange = new JPanel();
		panelOrange.setBackground(Color.ORANGE);
		panelOrange.setBounds(856, 132, 23, 20);
		frame.getContentPane().add(panelOrange);

		JPanel panelYellow = new JPanel();
		panelYellow.setBackground(Color.YELLOW);
		panelYellow.setBounds(895, 132, 23, 20);
		frame.getContentPane().add(panelYellow);

		JPanel panelGreen = new JPanel();
		panelGreen.setBackground(Color.GREEN);
		panelGreen.setBounds(770, 173, 23, 20);
		frame.getContentPane().add(panelGreen);

		JPanel panelGray = new JPanel();
		panelGray.setBackground(Color.GRAY);
		panelGray.setBounds(815, 173, 23, 20);
		frame.getContentPane().add(panelGray);

		JPanel panelMagenta = new JPanel();
		panelMagenta.setBackground(Color.MAGENTA);
		panelMagenta.setBounds(856, 173, 23, 20);
		frame.getContentPane().add(panelMagenta);

		JPanel panelCyan = new JPanel();
		panelCyan.setBackground(Color.CYAN);
		panelCyan.setBounds(895, 173, 23, 20);
		frame.getContentPane().add(panelCyan);

		JLabel lblColors = new JLabel("Цвета");
		lblColors.setBounds(772, 44, 45, 13);
		frame.getContentPane().add(lblColors);

		JLabel lblMainColor = new JLabel("<html>Основной цвет</html>");
		PropertyChangeListener propertyChangeListenerMainColor = propertyChangeEvent -> {
			if (crane != null) {
				crane.setMainColor(lblMainColor.getBackground());
				panel.repaint();
			}
		};
		lblMainColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblMainColor.addPropertyChangeListener("background", propertyChangeListenerMainColor);
		lblMainColor.setBounds(762, 67, 76, 26);
		frame.getContentPane().add(lblMainColor);

		JLabel lblDopColor = new JLabel("<html>Дополнительный цвет</html>");
		PropertyChangeListener propertyChangeListenerDopColor = propertyChangeEvent -> {
			if (crane != null) {
				if (crane instanceof HoistingCrane) {
					HoistingCrane tempCrane = (HoistingCrane) crane;
					tempCrane.setDopColor(lblDopColor.getBackground());
					crane = tempCrane;
					panel.repaint();
				}
			}
		};
		lblDopColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDopColor.addPropertyChangeListener("background", propertyChangeListenerDopColor);
		lblDopColor.setBounds(861, 67, 76, 26);
		frame.getContentPane().add(lblDopColor);

		var listenerForColor = new DragMouseAdapter();

		panelBlue.addMouseListener(listenerForColor);
		panelPink.addMouseListener(listenerForColor);
		panelOrange.addMouseListener(listenerForColor);
		panelYellow.addMouseListener(listenerForColor);
		panelGreen.addMouseListener(listenerForColor);
		panelGray.addMouseListener(listenerForColor);
		panelMagenta.addMouseListener(listenerForColor);
		panelCyan.addMouseListener(listenerForColor);
		lblDopColor.addMouseListener(listenerForColor);
		lblMainColor.addMouseListener(listenerForColor);

		panelBlue.setTransferHandler(new TransferHandler("background"));
		panelPink.setTransferHandler(new TransferHandler("background"));
		panelOrange.setTransferHandler(new TransferHandler("background"));
		panelYellow.setTransferHandler(new TransferHandler("background"));
		panelGreen.setTransferHandler(new TransferHandler("background"));
		panelGray.setTransferHandler(new TransferHandler("background"));
		panelMagenta.setTransferHandler(new TransferHandler("background"));
		panelCyan.setTransferHandler(new TransferHandler("background"));
		lblDopColor.setTransferHandler(new TransferHandler("background"));
		lblMainColor.setTransferHandler(new TransferHandler("background"));

		JButton btnOk = new JButton("Добавить");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (crane == null) {
					parkingForm.addCrane(null);
				} else if (crane instanceof HoistingCrane) {
					HoistingCrane tempCrane = (HoistingCrane) crane;
					parkingForm.addCrane(tempCrane);
				} else {
					TrackedVehicle tempCrane = (TrackedVehicle) crane;
					parkingForm.addCrane(tempCrane);
				}
				frame.dispose();
			}
		});
		btnOk.setBounds(738, 225, 95, 21);
		frame.getContentPane().add(btnOk);

		JButton btnCancel = new JButton("Отмена");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(852, 225, 85, 21);
		frame.getContentPane().add(btnCancel);

		JSpinner spinnerCountRinks = new JSpinner();
		spinnerCountRinks.setModel(new SpinnerNumberModel(4, 4, 6, 1));
		spinnerCountRinks.setBounds(208, 291, 44, 20);
		frame.getContentPane().add(spinnerCountRinks);

		JLabel lblPictureBox = new JLabel("");
		PropertyChangeListener propertyChangeListenerPlane = propertyChangeEvent -> {
			if (lblPictureBox.getText().equals("Гусеничная машина")) {
				crane = new TrackedVehicle((Integer) spinnerMaxSpeed.getValue(), (Integer) spinnerWeight.getValue(),
						Color.white);
			} else if (lblPictureBox.getText().equals("Подъемный кран")) {
				crane = new HoistingCrane((Integer) spinnerMaxSpeed.getValue(), (Integer) spinnerWeight.getValue(),
						Color.white, Color.gray, chckbxArrow.isSelected(), chckbxCounterweight.isSelected(),
						(Integer) spinnerCountRinks.getValue(), "Обыкновенные катки");
			} else if (lblPictureBox.getText().equals("Круги на катках")) {
				if (crane instanceof HoistingCrane) {
					HoistingCrane tempCrane = (HoistingCrane) crane;
					tempCrane.setIRink(new CircleRink((Integer) spinnerCountRinks.getValue()));
					crane = tempCrane;
				}
			} else if (lblPictureBox.getText().equals("Орнамент №1 на катках")) {
				if (crane instanceof HoistingCrane) {
					HoistingCrane tempCrane = (HoistingCrane) crane;
					tempCrane.setIRink(new Ornament1Rink((Integer) spinnerCountRinks.getValue()));
					crane = tempCrane;
				}
			} else if (lblPictureBox.getText().equals("Орнамент №2 на катках")) {
				if (crane instanceof HoistingCrane) {
					HoistingCrane tempCrane = (HoistingCrane) crane;
					tempCrane.setIRink(new Ornament2Rink((Integer) spinnerCountRinks.getValue()));
					crane = tempCrane;
				}
			}
			drawCrane(crane);
			lblPictureBox.setText("");
		};
		lblPictureBox.addPropertyChangeListener("text", propertyChangeListenerPlane);
		lblPictureBox.setBounds(334, 28, 366, 365);
		frame.getContentPane().add(lblPictureBox);

		var listenerForConfig = new DragMouseAdapter();

		lblTrackedVehicle.addMouseListener(listenerForConfig);
		lblHoistingCrane.addMouseListener(listenerForConfig);
		lblPictureBox.addMouseListener(listenerForConfig);

		lblTrackedVehicle.setTransferHandler(new TransferHandler("text"));
		lblHoistingCrane.setTransferHandler(new TransferHandler("text"));
		lblPictureBox.setTransferHandler(new TransferHandler("text"));

		JLabel lblCountRinks = new JLabel("Количетсво катков в гусеницах");
		lblCountRinks.setBounds(38, 294, 172, 13);
		frame.getContentPane().add(lblCountRinks);

		JLabel lblCirclesOnRinks = new JLabel("Круги на катках");
		lblCirclesOnRinks.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCirclesOnRinks.setBounds(766, 270, 130, 26);
		frame.getContentPane().add(lblCirclesOnRinks);

		JLabel lblOrnament1OnRinks = new JLabel("Орнамент №1 на катках");
		lblOrnament1OnRinks.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblOrnament1OnRinks.setBounds(766, 306, 152, 26);
		frame.getContentPane().add(lblOrnament1OnRinks);

		JLabel lblOrnament2OnRinks = new JLabel("Орнамент №2 на катках");
		lblOrnament2OnRinks.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblOrnament2OnRinks.setBounds(766, 342, 152, 26);
		frame.getContentPane().add(lblOrnament2OnRinks);

		var listenerForFloat = new DragMouseAdapter();

		lblCirclesOnRinks.addMouseListener(listenerForFloat);
		lblOrnament1OnRinks.addMouseListener(listenerForFloat);
		lblOrnament2OnRinks.addMouseListener(listenerForFloat);

		lblCirclesOnRinks.setTransferHandler(new TransferHandler("text"));
		lblOrnament1OnRinks.setTransferHandler(new TransferHandler("text"));
		lblOrnament2OnRinks.setTransferHandler(new TransferHandler("text"));

		frame.repaint();
	}

	private void drawCrane(Platform crane) {
		if (crane != null) {
			panel.setCrane(crane);
			crane.setPosition(25, 250, panel.getWidth(), panel.getHeight());
			panel.repaint();
		}
	}
}
