package Interfaces;

import java.awt.Color;
import java.awt.Graphics;

import Enums.Direction;

public interface ICrane {
	// Установка позиции подъемного крана
	// "x" Координата X
	// "y" Координата Y
	// "width" Ширина картинки
	// "height" Высота картинки
	void setPosition(int x, int y, int width, int height);

	// Изменение направления перемещения
	// "direction" Направление
	void moveCrane(Direction direction);

	// Отрисовка
	void drawCrane(Graphics g);

	// Смена основного цвета
	void setMainColor(Color color);
}