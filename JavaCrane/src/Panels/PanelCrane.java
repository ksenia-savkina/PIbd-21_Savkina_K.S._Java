package Panels;

import java.awt.Graphics;

import javax.swing.JPanel;

import Interfaces.ICrane;

public class PanelCrane extends JPanel {
	private ICrane crane;
	private boolean craneIsSet;

	public PanelCrane(boolean craneIsSet) {
		this.craneIsSet = craneIsSet;
	}

	public void setCrane(ICrane crane) {
		this.crane = crane;
		craneIsSet = true;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (craneIsSet) {
			crane.drawCrane(g);
		}
	}
}
