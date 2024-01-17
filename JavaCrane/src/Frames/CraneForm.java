package Frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Enums.Direction;

import HoistingCranePckg.HoistingCrane;
import HoistingCranePckg.TrackedVehicle;

import Interfaces.ICrane;

import Panels.PanelCrane;

public class CraneForm {

	private JFrame frame;
	private JTextField txtCount;
	private JLabel label;
	private ICrane crane;
	private PanelCrane panel;
	private Integer k;

	public CraneForm() {
		frame = new JFrame("Подъемный кран");
		frame.setBounds(100, 100, 1330, 770);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);

		txtCount = new JTextField();
		txtCount.setEditable(false);
		txtCount.setBounds(5, 227, 100, 20);
		frame.getContentPane().add(txtCount);
		txtCount.setColumns(10);

		label = new JLabel();
		label.setBounds(13, 166, 71, 50);
		frame.getContentPane().add(label);

		panel = new PanelCrane(false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(108, 10, 1086, 722);
		frame.getContentPane().add(panel);

		JButton btnCreateTrackedVehicle = new JButton("<html>Создать гусеничную машину</html>");
		btnCreateTrackedVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createObj("<html>Количетсво катков в гусеницах:</html>", "4",
						new TrackedVehicle(rnd(1, 3), rnd(25, 50), Color.green), rnd(20, 100));
			}
		});
		btnCreateTrackedVehicle.setBounds(10, 11, 89, 59);
		frame.getContentPane().add(btnCreateTrackedVehicle);

		JButton btnCreateHoistingCrane = new JButton("<html>Создать подъемный кран</html>");
		btnCreateHoistingCrane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				k = rnd(4, 6);
				createObj(
						"<html>Количетсво катков в гусеницах:</html>", k.toString(), new HoistingCrane(rnd(1, 3),
								rnd(25, 50), Color.green, Color.gray, true, true, k, "Обыкновенные катки"),
						rnd(230, 325));
			}
		});
		btnCreateHoistingCrane.setBounds(10, 81, 89, 59);
		frame.getContentPane().add(btnCreateHoistingCrane);

		JButton btnLeft = new JButton();
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crane.moveCrane(Direction.Left);
				panel.repaint();
			}
		});
		btnLeft.setBounds(1204, 702, 30, 30);
		btnLeft.setIcon(new ImageIcon("img/left.jpg"));
		frame.getContentPane().add(btnLeft);

		JButton btnRight = new JButton();
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crane.moveCrane(Direction.Right);
				panel.repaint();
			}
		});
		btnRight.setBounds(1286, 702, 30, 30);
		btnRight.setIcon(new ImageIcon("img/right.jpg"));
		frame.getContentPane().add(btnRight);

		JButton btnUp = new JButton();
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crane.moveCrane(Direction.Up);
				panel.repaint();
			}
		});
		btnUp.setBounds(1246, 662, 30, 30);
		btnUp.setIcon(new ImageIcon("img/up.jpg"));
		frame.getContentPane().add(btnUp);

		JButton btnDown = new JButton();
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crane.moveCrane(Direction.Down);
				panel.repaint();
			}
		});
		btnDown.setBounds(1246, 702, 30, 30);
		btnDown.setIcon(new ImageIcon("img/down.jpg"));
		frame.getContentPane().add(btnDown);

		JButton btnAddDopRinks1 = new JButton("<html>Добавить круги на катках</html>");
		btnAddDopRinks1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createObj("<html>Тип фигуры</html>", "Круг", new HoistingCrane(rnd(1, 3), rnd(25, 50), Color.green,
						Color.gray, true, true, k, "Круги на катках"), rnd(230, 325));
			}
		});
		btnAddDopRinks1.setBounds(12, 302, 89, 59);
		frame.getContentPane().add(btnAddDopRinks1);

		JButton btnAddDopRinks2 = new JButton("<html>Добавить орнамент №1 на катках</html>");
		btnAddDopRinks2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createObj("<html>Тип орнамента</html>", "№1", new HoistingCrane(rnd(1, 3), rnd(25, 50), Color.green,
						Color.gray, true, true, k, "Орнамент №1 на катках"), rnd(230, 325));
			}
		});
		btnAddDopRinks2.setBounds(11, 366, 89, 90);
		frame.getContentPane().add(btnAddDopRinks2);

		JButton btnAddDopRinks3 = new JButton("<html>Добавить орнамент №2 на катках</html>");
		btnAddDopRinks3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createObj("<html>Тип орнамента</html>", "№2", new HoistingCrane(rnd(1, 3), rnd(25, 50), Color.green,
						Color.gray, true, true, k, "Орнамент №2 на катках"), rnd(230, 325));
			}
		});
		btnAddDopRinks3.setBounds(11, 467, 89, 90);
		frame.getContentPane().add(btnAddDopRinks3);

		frame.repaint();
	}

	// Передача крана на форму
	public void setCrane(ICrane hcrane) {
		crane = hcrane;
		panel.setCrane(crane);
		crane.setPosition(rnd(0, 100), rnd(230, 325), panel.getWidth(), panel.getHeight());
		panel.repaint();
	}

	private void createObj(String textLbl, String textTxtFld, ICrane hcrane, int craneY) {
		label.setText(textLbl);
		txtCount.setText(textTxtFld);
		crane = hcrane;
		panel.setCrane(crane);
		crane.setPosition(rnd(0, 100), craneY, panel.getWidth(), panel.getHeight());
		panel.repaint();
	}

	private int rnd(int min, int max) {
		max -= min;
		return (int) (Math.random() * ++max) + min;
	}
}
