package Interfaces;

import java.awt.Color;
import java.awt.Graphics;

public interface IRink {
	void setKol(int k);
	void drawRink(int x, int y, Graphics g, Color color);
}
