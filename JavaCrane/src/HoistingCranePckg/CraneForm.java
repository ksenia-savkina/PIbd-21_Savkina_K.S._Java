package HoistingCranePckg;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CraneForm {

	private JFrame frame;
	private JTextField txtCount;
	HoistingCrane crane;
	Rink rink;
	PanelCrane panel;
	Integer k;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CraneForm window = new CraneForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CraneForm() {
		initialize();
	}

	public int rnd(int min, int max) {
		max -= min;
		return (int) (Math.random() * ++max) + min;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtCount = new JTextField();
		txtCount.setEditable(false);
		txtCount.setBounds(10, 111, 86, 20);
		frame.getContentPane().add(txtCount);
		txtCount.setColumns(10);

		k = rnd(4, 6);
		crane = new HoistingCrane(rnd(1, 3), rnd(25, 50), Color.green, Color.gray, true, true, k);
		panel = new PanelCrane(crane, false);

		crane.setPosition(rnd(0, 100), rnd(20, 100), panel.getWidth(), panel.getHeight());
		panel.setBackground(Color.WHITE);
		panel.setBounds(108, 0, 651, 461);
		frame.getContentPane().add(panel);

		JButton btnCreate = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.drawCan(true);
				txtCount.setText(k.toString());
				crane.setPosition(rnd(0, 100), rnd(20, 100), panel.getWidth(), panel.getHeight());
				panel.repaint();
			}
		});
		btnCreate.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnCreate);

		JButton btnLeft = new JButton();
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crane.moveCrane(Direction.Left);
				panel.repaint();
			}
		});
		btnLeft.setBounds(764, 431, 30, 30);
		btnLeft.setIcon(new ImageIcon("img/left.jpg"));
		frame.getContentPane().add(btnLeft);

		JButton btnRight = new JButton();
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crane.moveCrane(Direction.Right);
				panel.repaint();
			}
		});
		btnRight.setBounds(844, 431, 30, 30);
		btnRight.setIcon(new ImageIcon("img/right.jpg"));
		frame.getContentPane().add(btnRight);

		JButton btnUp = new JButton();
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crane.moveCrane(Direction.Up);
				panel.repaint();
			}
		});
		btnUp.setBounds(804, 391, 30, 30);
		btnUp.setIcon(new ImageIcon("img/up.jpg"));
		frame.getContentPane().add(btnUp);

		JButton btnDown = new JButton();
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crane.moveCrane(Direction.Down);
				panel.repaint();
			}
		});
		btnDown.setBounds(804, 431, 30, 30);
		btnDown.setIcon(new ImageIcon("img/down.jpg"));
		frame.getContentPane().add(btnDown);

		JLabel label = new JLabel(
				"<html>\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043A\u0430\u0442\u043A\u043E\u0432 \u0432 \u0433\u0443\u0441\u0435\u043D\u0438\u0446\u0430\u0445:");
		label.setBounds(10, 50, 71, 50);
		frame.getContentPane().add(label);
	}
}
