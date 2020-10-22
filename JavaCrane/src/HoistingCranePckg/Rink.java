package HoistingCranePckg;

import java.awt.Color;
import java.awt.Graphics;
import Enums.Rinks;
import Interfaces.IRink;

public class Rink implements IRink {

	private Rinks kol;

	@Override
	public void setKol(int k) {
		this.kol = Rinks.countRinks(k);
	}

	public Rink(int k) {
		setKol(k);
	}

	private void drawCount(int x, int y, int diameter, int count, Graphics g) {
		for (int i = 0; i < count; i++) {
			g.fillOval(x + diameter * i, y, diameter, diameter);
		}
	}

	@Override
	public void drawRink(int x, int y, Graphics g, Color color) {
		g.setColor(color);
		switch (kol) {
		case Four:
			drawCount(x, y, 47, 4, g);
			break;
		case Five:
			drawCount(x, y + 4, 38, 5, g);
			break;
		case Six:
			drawCount(x, y + 6, 31, 6, g);
			break;
		}
	}
}
