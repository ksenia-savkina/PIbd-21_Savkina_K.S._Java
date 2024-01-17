package HoistingCranePckg;

import java.awt.Color;
import java.awt.Graphics;

import Enums.Rinks;

import Interfaces.IRink;

public class CircleRink implements IRink {

	private Rinks kol;

	@Override
	public void setKol(int k) {
		this.kol = Rinks.countRinks(k);
	}

	public CircleRink(int k) {
		setKol(k);
	}

	private void drawCount(int x, int y, int diameterBig, int diameterSmall, int count, Color color, Graphics g) {
		for (int i = 0; i < count; i++) {
			g.setColor(color);
			g.fillOval(x + diameterBig * i, y, diameterBig, diameterBig);
			g.setColor(Color.black);
			g.fillOval(x + (diameterBig - diameterSmall) / 2 + diameterBig * i, y + (diameterBig - diameterSmall) / 2,
					diameterSmall, diameterSmall);
		}
	}

	@Override
	public void drawRink(int x, int y, Graphics g, Color color) {
		switch (kol) {
		case Four:
			drawCount(x, y, 47, 19, 4, color, g);
			break;
		case Five:
			drawCount(x, y + 4, 38, 15, 5, color, g);
			break;
		case Six:
			drawCount(x, y + 6, 31, 12, 6, color, g);
			break;
		}
	}
}
