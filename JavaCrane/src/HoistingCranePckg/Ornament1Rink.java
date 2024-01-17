package HoistingCranePckg;

import java.awt.Color;
import java.awt.Graphics;

import Enums.Rinks;

import Interfaces.IRink;

public class Ornament1Rink implements IRink {

	private Rinks kol;

	@Override
	public void setKol(int k) {
		this.kol = Rinks.countRinks(k);
	}

	public Ornament1Rink(int k) {
		setKol(k);
	}

	private void ornament1(int x, int y, int diameter, Graphics g) {
		g.drawLine(x + diameter / 2, y, x + diameter / 2, y + diameter / 2);
		g.drawLine(x + diameter / 4, y + diameter - 5, x + diameter / 2, y + diameter / 2);
		g.drawLine(x + diameter / 2 + diameter / 4, y + diameter - 5, x + diameter / 2, y + diameter / 2);
	}

	private void drawCount(int x, int y, int diameter, int count, Color color, Graphics g) {
		for (int i = 0; i < count; i++) {
			g.setColor(color);
			g.fillOval(x + diameter * i, y, diameter, diameter);
			g.setColor(Color.black);
			ornament1(x + diameter * i, y, diameter, g);
		}
	}

	@Override
	public void drawRink(int x, int y, Graphics g, Color color) {
		switch (kol) {
		case Four:
			drawCount(x, y, 47, 4, color, g);
			break;
		case Five:
			drawCount(x, y + 4, 38, 5, color, g);
			break;
		case Six:
			drawCount(x, y + 6, 31, 6, color, g);
			break;
		}
	}
}
