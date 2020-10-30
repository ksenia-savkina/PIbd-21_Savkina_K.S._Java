package HoistingCranePckg;

import java.awt.Color;
import java.awt.Graphics;
import Interfaces.ICrane;
import Interfaces.IRink;

public class Parking<T extends ICrane, I extends IRink> {
	// Массив объектов, которые храним
	private final Object[] _places;
	// Ширина окна отрисовки
	private final int pictureWidth;
	// Высота окна отрисовки
	private final int pictureHeight;
	// Размер парковочного места (ширина)
	private final int _placeSizeWidth = 240;
	// Размер парковочного места (высота)
	private final int _placeSizeHeight = 350;

	// Конструктор
	// "picWidth" Размер стоянки - ширина
	// "picHeight" Размер стоянки - высота
	public Parking(int picWidth, int picHeight) {
		int width = picWidth / _placeSizeWidth;
		int height = picHeight / _placeSizeHeight;
		_places = new Object[width * height];
		pictureWidth = picWidth;
		pictureHeight = picHeight;
	}

	// Перегрузка оператора сложения
	// Логика действия: на стоянку добавляется кран
	// "p" Стоянка
	// "crane" Добавляемый кран
	public boolean addition(T crane) {
		int interval = 35;
		int x = 5;
		int y = 235;
		int placesWidth = pictureWidth / _placeSizeWidth;
		for (int i = 0; i < _places.length; i++) {
			if (_places[i] == null) {
				_places[i] = crane;
				crane.setPosition(x + (_placeSizeWidth + interval) * (i % placesWidth),
						y + _placeSizeHeight * (i / placesWidth), pictureWidth, pictureHeight);
				return true;
			}
		}
		return false;
	}

	// Перегрузка оператора вычитания
	// Логика действия: с парковки забираем кран
	// "p" Стоянка
	// "index" Индекс места, с которого пытаемся извлечь объект
	public T subtraction(int index) {
		if (index >= 0 && index < _places.length && _places[index] != null) {
			Object temp = _places[index];
			_places[index] = null;
			return (T) temp;
		}
		return null;
	}

	private int countOccupiedPlaces() {
		int k = 0;
		for (int i = 0; i < _places.length; i++) {
			if (_places[i] != null) {
				k++;
			}
		}
		return k;
	}

	public boolean more(int kol) {
		return countOccupiedPlaces() > kol;
	}

	public boolean less(int kol) {
		return countOccupiedPlaces() < kol;
	}

	// Метод отрисовки стоянки
	public void draw(Graphics g) {
		drawMarking(g);
		for (int i = 0; i < _places.length; i++)
			if (_places[i] != null) {
				T placeT = (T) _places[i];
				placeT.drawCrane(g);
			}
	}

	// Метод отрисовки разметки парковочных мест
	private void drawMarking(Graphics g) {
		int x = 0;
		int interval = 35;
		g.setColor(Color.black);
		for (int i = 0; i < pictureWidth / _placeSizeWidth; i++) {
			for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; ++j) {// линия рамзетки места
				g.drawLine(x + (_placeSizeWidth + interval) * i, j * _placeSizeHeight,
						x + _placeSizeWidth + (_placeSizeWidth + interval) * i, j * _placeSizeHeight);
			}
			g.drawLine(i * (_placeSizeWidth + interval), 0, i * (_placeSizeWidth + interval),
					(pictureHeight / _placeSizeHeight) * _placeSizeHeight);
		}
	}

}
