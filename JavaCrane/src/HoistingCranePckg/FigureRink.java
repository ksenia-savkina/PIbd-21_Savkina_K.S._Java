package HoistingCranePckg;

import java.awt.Color;
import java.awt.Graphics;
import Enums.Figures;
import Interfaces.IRink;

public class FigureRink implements IRink {

	private Figures kol;

	@Override
	public void setKol(int k) {
		this.kol = Figures.figureKind(k);
	}

	public FigureRink(int k) {
		setKol(k);
	}

	@Override
	public void drawRink(int x, int y, Graphics g, Color color) {
		int diameterBig = 47;
		int diameterSmall = 19;
		int length = (diameterBig - diameterSmall) / 2;
		g.setColor(color);
		for (int i = 0; i < 4; i++) {
			switch (kol) {
			case Circle:
				g.fillOval(x + length + diameterBig * i, y + length, diameterSmall, diameterSmall);
				break;
			case Square:
				g.fillRect(x + length + diameterBig * i, y + length, diameterSmall, diameterSmall);
				break;
			}
		}
	}
}
