package HoistingCranePckg;

import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelCrane extends JPanel {
	HoistingCrane crane;
	private boolean canDraw;

	public PanelCrane(HoistingCrane hCrane, boolean CanDraw) {
		crane = hCrane;
		canDraw = CanDraw;
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
