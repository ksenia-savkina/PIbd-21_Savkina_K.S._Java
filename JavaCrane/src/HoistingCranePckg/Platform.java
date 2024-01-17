package HoistingCranePckg;

import java.awt.Color;
import java.awt.Graphics;

import Enums.Direction;

import Interfaces.ICrane;

public abstract class Platform implements ICrane {
	// Левая координата отрисовки гусеничной машины
	protected int _startPosX;
	// Правая кооридната отрисовки гусеничной машины
	protected int _startPosY;
	// Максимальная скорость гусеничной машины
	public int maxSpeed;
	// Общая масса агрегата
	public float weight;
	// Основной цвет
	public Color mainColor;
	// Ширина окна отрисовки
	protected int _pictureWidth;
	// Высота окна отрисовки
	protected int _pictureHeight;

	public abstract void drawCrane(Graphics g);

	public abstract void moveCrane(Direction direction);

	public void setPosition(int x, int y, int width, int height) {
		_pictureHeight = height;
		_pictureWidth = width;
		_startPosX = x;
		_startPosY = y;
	}

	public void setMainColor(Color color) {
		mainColor = color;
	}
}
