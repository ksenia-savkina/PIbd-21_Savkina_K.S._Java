package HoistingCranePckg;

import java.awt.Color;
import java.awt.Graphics;
import Enums.Ornaments;
import Interfaces.IRink;

public class OrnamentRink implements IRink {

	private Ornaments kol;

	@Override
	public void setKol(int k) {
		this.kol = Ornaments.ornamentKind(k);
	}

	public OrnamentRink(int k) {
		setKol(k);
	}

	private void ornament1(int x, int y, int diameter, Graphics g) {
		g.drawLine(x + diameter / 2, y, x + diameter / 2, y + diameter / 2);
		g.drawLine(x + diameter / 4, y + diameter - 5, x + diameter / 2, y + diameter / 2);
		g.drawLine(x + diameter / 2 + diameter / 4, y + diameter - 5, x + diameter / 2, y + diameter / 2);
	}

	private void ornament2(int x, int y, int diameter, Graphics g) {
		g.drawLine(x + diameter / 2, y, x + diameter / 2, y + diameter);
		g.drawLine(x, y + diameter / 2, x + diameter, y + diameter / 2);
	}

	@Override
	public void drawRink(int x, int y, Graphics g, Color color) {
		g.setColor(color);
		for (int i = 0; i < 4; i++) {
			switch (kol) {
			case Ornament1:
				ornament1(x + 47 * i, y, 47, g);
				break;
			case Ornament2:
				ornament2(x + 47 * i, y, 47, g);
				break;
			}
		}
	}
}
