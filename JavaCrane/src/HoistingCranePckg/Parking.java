package HoistingCranePckg;

import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

import Exceptions.CraneNotFoundException;
import Exceptions.ParkingAlreadyHaveException;
import Exceptions.ParkingOverflowException;
import Interfaces.ICrane;
import Interfaces.IRink;

public class Parking<T extends ICrane, I extends IRink> {

	public CraneComparer craneComparator = new CraneComparer();
	// Список объектов, которые храним
	private final List<T> _places;
	// Максимальное количество мест на стоянке
	private final int _maxCount;
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
		_maxCount = width * height;
		pictureWidth = picWidth;
		pictureHeight = picHeight;
		_places = new ArrayList<>();
	}

	// Перегрузка оператора сложения
	// Логика действия: на стоянку добавляется кран
	// "p" Стоянка
	// "crane" Добавляемый кран
	public boolean addition(T crane) throws ParkingOverflowException, ParkingAlreadyHaveException {
		if (_places.size() >= _maxCount) {
			throw new ParkingOverflowException();
		}
		if (_places.contains(crane)) {
			throw new ParkingAlreadyHaveException();
		}
		_places.add(crane);
		return true;
	}

	// Перегрузка оператора вычитания
	// Логика действия: с парковки забираем кран
	// "p" Стоянка
	// "index" Индекс места, с которого пытаемся извлечь объект
	public T subtraction(int index) throws CraneNotFoundException {
		if (index < -1 || index >= _places.size()) {
			throw new CraneNotFoundException(index);
		}
		T crane = _places.get(index);
		_places.remove(index);
		return crane;
	}

	public boolean more(int kol) {
		return _places.size() > kol;
	}

	public boolean less(int kol) {
		return _places.size() < kol;
	}

	// Метод отрисовки стоянки
	public void draw(Graphics g) {
		drawMarking(g);
		int interval = 35;
		int x = 5;
		int y = 235;
		int placesWidth = pictureWidth / _placeSizeWidth;
		for (int i = 0; i < _places.size(); ++i) {
			_places.get(i).setPosition(x + (_placeSizeWidth + interval) * (i % placesWidth),
					y + _placeSizeHeight * (i / placesWidth), pictureWidth, pictureHeight);
			_places.get(i).drawCrane(g);
		}
	}

	// Метод отрисовки разметки парковочных мест
	private void drawMarking(Graphics g) {
		int x = 0;
		int interval = 35;
		g.setColor(Color.black);
		for (int i = 0; i < pictureWidth / _placeSizeWidth; i++) {
			for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; ++j) {// линия разметки места
				g.drawLine(x + (_placeSizeWidth + interval) * i, j * _placeSizeHeight,
						x + _placeSizeWidth + (_placeSizeWidth + interval) * i, j * _placeSizeHeight);
			}
			g.drawLine(i * (_placeSizeWidth + interval), 0, i * (_placeSizeWidth + interval),
					(pictureHeight / _placeSizeHeight) * _placeSizeHeight);
		}
	}

	public T get(int index) {
		if (index >= 0 && index < _places.size()) {
			return _places.get(index);
		}
		return null;
	}

	public void clear() {
		_places.clear();
	}

	public void sort() {
		_places.sort(craneComparator);
	}
}
