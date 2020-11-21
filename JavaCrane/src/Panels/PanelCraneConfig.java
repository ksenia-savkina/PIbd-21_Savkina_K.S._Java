package Panels;

import java.awt.Graphics;

import javax.swing.JPanel;

import HoistingCranePckg.Platform;

public class PanelCraneConfig extends JPanel {
	private Platform crane;
	private boolean craneIsSet;

	public PanelCraneConfig(boolean craneIsSet) {
		this.craneIsSet = craneIsSet;
	}

	public void setCrane(Platform crane) {
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