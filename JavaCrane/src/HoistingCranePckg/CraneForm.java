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
import Enums.Direction;
import Interfaces.ICrane;
import Interfaces.IRink;

public class CraneForm {

	private JFrame frame;
	private JTextField txtCount;
	private JLabel label;
	private ICrane crane;
	private IRink rink;
	private PanelCrane panel;
	private Integer k;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Подъемный кран");
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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
		panel.setBounds(108, 0, 651, 461);
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
				createObj("<html>Количетсво катков в гусеницах:</html>", k.toString(),
						new HoistingCrane(rnd(1, 3), rnd(25, 50), Color.green, Color.gray, true, true, k, 0),
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

		JButton btnAddDopRinks1 = new JButton("<html>Добавить фигуры на катках</html>");
		btnAddDopRinks1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				k = rnd(1, 2);
				String str;
				if (k == 1)
					str = "Круг";
				else
					str = "Квадрат";
				createObj("<html>Тип фигуры</html>", str,
						new HoistingCrane(rnd(1, 3), rnd(25, 50), Color.green, Color.gray, true, true, k, 1),
						rnd(230, 325));
			}
		});
		btnAddDopRinks1.setBounds(12, 302, 89, 59);
		frame.getContentPane().add(btnAddDopRinks1);

		JButton btnAddDopRinks2 = new JButton("<html>Добавить орнаменты на катках</html>");
		btnAddDopRinks2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k = rnd(1, 2);
				String str;
				if (k == 1)
					str = "Орнамент № 1";
				else
					str = "Орнамент № 2";
				createObj("<html>Тип орнамента</html>", str,
						new HoistingCrane(rnd(1, 3), rnd(25, 50), Color.green, Color.gray, true, true, k, 2),
						rnd(230, 325));
			}
		});
		btnAddDopRinks2.setBounds(11, 366, 89, 59);
		frame.getContentPane().add(btnAddDopRinks2);
	}

	private void createObj(String textLbl, String textTxtFld, ICrane hcrane, int craneY) {
		label.setText(textLbl);
		txtCount.setText(textTxtFld);
		panel.drawCan(true);
		crane = hcrane;
		panel.setTrackedVehicle(crane);
		crane.setPosition(rnd(0, 100), craneY, panel.getWidth(), panel.getHeight());
		panel.repaint();
	}

	private int rnd(int min, int max) {
		max -= min;
		return (int) (Math.random() * ++max) + min;
	}
}
