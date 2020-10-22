package HoistingCranePckg;

import java.awt.Graphics;
import javax.swing.JPanel;
import Interfaces.ICrane;

public class PanelCrane extends JPanel {
	private ICrane crane;
	private boolean canDraw;

	public PanelCrane(boolean CanDraw) {
		canDraw = CanDraw;
	}

	public void setTrackedVehicle(ICrane crane) {
		this.crane = crane;
	}

	public boolean drawCan(boolean can) {
		canDraw = can;
		if (canDraw == false) {
			return false;
		}
		return true;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (canDraw == true) {
			crane.drawCrane(g);
		}
	}
}
