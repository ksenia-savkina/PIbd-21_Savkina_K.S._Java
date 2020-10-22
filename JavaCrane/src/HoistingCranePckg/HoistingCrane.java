package HoistingCranePckg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import Enums.Direction;
import Interfaces.IRink;

public class HoistingCrane extends TrackedVehicle {
	// Высота отрисовки стрелы
	private final int _arrowHeight = 230;
	// Высота отрисовки противовеса
	private final int _counterweightHeight = 70;
	// Ширина отрисовки противовеса
	private final int _counterweightWidth = 30;
	// Дополнительный цвет
	public Color dopColor;
	// Признак наличия стрелы
	public boolean arrow;
	// Признак наличия противовеса
	public boolean counterweight;

	private IRink rink;

	// Конструктор
	// "maxSpeed" Максимальная скорость(м/мин)
	// "weight" Общая масса агрегата(т)
	// "mainColor" Основной цвет
	// "dopColor" Дополнительный цвет

	// "cabin" Признак наличия кабины
	// "arrow" Признак наличия стрелы
	// "counterweight" Признак наличия противовеса(м)
	public HoistingCrane(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean arrow,
			boolean counterweight, int count, int dop) {
		super(maxSpeed, weight, mainColor, 200, 110, getBaseRinks(dop));
		this.dopColor = dopColor;
		this.arrow = arrow;
		this.counterweight = counterweight;
		switch (dop) {
		case 0:
			rink = new Rink(count);
			break;
		case 1:
			rink = new FigureRink(count);
			break;
		case 2:
			rink = new OrnamentRink(count);
			break;
		}
	}

	private static boolean getBaseRinks(int k) {
		switch (k) {
		case 0:
			return false;
		case 1:
			return true;
		case 2:
			return true;
		}
		return false;
	}

	@Override
	// Изменение направления перемещения
	public void moveCrane(Direction direction) {
		float step = maxSpeed * 100 / weight;
		switch (direction) {
		// вправо
		case Right:
			if (counterweight) {
				if (_startPosX + step < _pictureWidth - (trackedVehicleWidth + _counterweightWidth)) {
					_startPosX += step;
				}
			} else {
				super.moveCrane(Direction.Right);
			}
			break;
		// влево
		case Left:
			super.moveCrane(Direction.Left);
			break;
		// вверх
		case Up:
			if (arrow || counterweight) {
				if (arrow || arrow && counterweight) {
					if (_startPosY - step > _arrowHeight) {
						_startPosY -= step;
					}
				} else if (counterweight) {
					if (_startPosY - step > _counterweightHeight) {
						_startPosY -= step;
					}
				}
			} else {
				super.moveCrane(Direction.Up);
			}
			break;
		// вниз
		case Down:
			super.moveCrane(Direction.Down);
			break;
		}
	}

	@Override
	// Отрисовка
	public void drawCrane(Graphics g) {
		super.drawCrane(g);
		rink.drawRink(_startPosX, _startPosY + 63, g, dopColor);

		if (counterweight) {
			g.setColor(dopColor);
			g.fillRect(_startPosX + 200, _startPosY - 10, 30, 60);
			g.fillOval(_startPosX + 190, _startPosY - 70, 20, 20);
			g.setColor(Color.black);
			g.drawRect(_startPosX + 200, _startPosY - 10, 30, 60);
			g.drawOval(_startPosX + 190, _startPosY - 70, 20, 20);
			g.drawLine(_startPosX + 200, _startPosY - 10, _startPosX + 200, _startPosY - 60);
			g.drawLine(_startPosX + 120, _startPosY, _startPosX + 200, _startPosY - 60);
		}
		if (arrow) {
			g.setColor(dopColor);
			g.fillRect(_startPosX + 10, _startPosY - 230, 20, 20);
			g.fillOval(_startPosX, _startPosY - 230, 10, 10);
			g.fillOval(_startPosX + 2, _startPosY - 140, 6, 10);
			g.fillOval(_startPosX + 10, _startPosY - 110, 10, 15);

			g.setColor(Color.black);
			g.drawLine(_startPosX + 30, _startPosY - 230, _startPosX + 200, _startPosY - 60);
			g.drawLine(_startPosX + 70, _startPosY - 100, _startPosX + 120, _startPosY);
			g.drawRect(_startPosX + 10, _startPosY - 230, 20, 20);
			g.drawOval(_startPosX, _startPosY - 230, 10, 10);
			g.drawLine(_startPosX + 5, _startPosY - 225, _startPosX + 5, _startPosY - 140);
			g.drawOval(_startPosX + 2, _startPosY - 140, 6, 10);
			g.drawLine(_startPosX + 10, _startPosY - 210, _startPosX + 10, _startPosY - 100);
			g.drawLine(_startPosX + 20, _startPosY - 210, _startPosX + 20, _startPosY - 100);
			g.drawOval(_startPosX + 10, _startPosY - 110, 10, 15);

			Polygon points2 = new Polygon();
			points2.addPoint(_startPosX + 70, _startPosY + 30);
			points2.addPoint(_startPosX + 70, _startPosY - 100);
			points2.addPoint(_startPosX + 20, _startPosY - 230);
			points2.addPoint(_startPosX + 30, _startPosY - 100);
			g.drawPolygon(points2);

			g.drawArc(_startPosX + 10, _startPosY - 95, 10, 10, -180, 270);

			g.drawLine(_startPosX + 23, _startPosY - 190, _startPosX + 34, _startPosY - 190);
			g.drawLine(_startPosX + 26, _startPosY - 150, _startPosX + 50, _startPosY - 150);

			g.drawLine(_startPosX + 23, _startPosY - 190, _startPosX + 50, _startPosY - 150);
			g.drawLine(_startPosX + 26, _startPosY - 150, _startPosX + 34, _startPosY - 190);

			g.drawLine(_startPosX + 26, _startPosY - 150, _startPosX + 67, _startPosY - 110);
			g.drawLine(_startPosX + 30, _startPosY - 110, _startPosX + 50, _startPosY - 150);

			g.drawLine(_startPosX + 30, _startPosY - 110, _startPosX + 67, _startPosY - 110);
			g.drawLine(_startPosX + 33, _startPosY - 90, _startPosX + 69, _startPosY - 90);

			g.drawLine(_startPosX + 46, _startPosY - 50, _startPosX + 69, _startPosY - 90);
			g.drawLine(_startPosX + 33, _startPosY - 90, _startPosX + 70, _startPosY - 50);

			g.drawLine(_startPosX + 46, _startPosY - 50, _startPosX + 70, _startPosY - 50);
			g.drawLine(_startPosX + 59, _startPosY - 10, _startPosX + 70, _startPosY - 10);

			g.drawLine(_startPosX + 46, _startPosY - 50, _startPosX + 70, _startPosY - 10);
			g.drawLine(_startPosX + 59, _startPosY - 10, _startPosX + 70, _startPosY - 50);
		}
	}
}
