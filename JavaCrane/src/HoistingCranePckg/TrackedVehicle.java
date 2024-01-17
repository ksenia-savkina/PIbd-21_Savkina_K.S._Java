package HoistingCranePckg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Iterator;
import java.util.LinkedList;

import Enums.Direction;

public class TrackedVehicle extends Platform implements Iterator<Object>, Iterable<Object>, Comparable<TrackedVehicle> {

	public LinkedList<Object> objectProperties = new LinkedList<Object>();

	// Ширина отрисовки гусеничной машины
	protected int trackedVehicleWidth = 200;
	// Высота отрисовки гусеничной машины
	protected int trackedVehicleHeight = 110;
	// Высота отрисовки кабины
	protected final int _cabinHeight = 20;

	protected boolean isBaseRinks = true;

	// Разделитель для записи информации по объекту в файл
	protected final String separator = ";";

	private int currentIndex = 0;

	@Override
	public boolean hasNext() {
		return currentIndex++ < 3;
	}

	@Override
	public Object next() {
		return objectProperties.get(currentIndex);
	}

	@Override
	public void remove() {
		objectProperties.remove(currentIndex);
	}

	@Override
	public Iterator<Object> iterator() {
		return objectProperties.iterator();
	}

	public TrackedVehicle(int maxSpeed, float weight, Color mainColor) {
		this.maxSpeed = maxSpeed;
		this.weight = weight;
		this.mainColor = mainColor;
	}

	protected TrackedVehicle(int maxSpeed, float weight, Color mainColor, int trackedVehicleWidth,
			int trackedVehicleHeight, boolean isBaseRinks) {
		this.maxSpeed = maxSpeed;
		objectProperties.add(maxSpeed);
		this.weight = weight;
		objectProperties.add(weight);
		this.mainColor = mainColor;
		objectProperties.add(mainColor);
		this.trackedVehicleWidth = trackedVehicleWidth;
		this.trackedVehicleHeight = trackedVehicleHeight;
		this.isBaseRinks = isBaseRinks;
	}

	public TrackedVehicle(String info) {
		String[] strs = info.split(separator);
		if (strs.length == 3) {
			maxSpeed = Integer.parseInt(strs[0]);
			objectProperties.add(maxSpeed);
			weight = Float.parseFloat(strs[1]);
			objectProperties.add(weight);
			mainColor = new Color(Integer.parseInt(strs[2]));
			objectProperties.add(mainColor);
		}
	}

	@Override
	public void moveCrane(Direction direction) {
		float step = maxSpeed * 100 / weight;
		switch (direction) {
		// вправо
		case Right:
			if (_startPosX + step < _pictureWidth - trackedVehicleWidth) {
				_startPosX += step;
			}

			break;
		// влево
		case Left:
			if (_startPosX - step > 0) {
				_startPosX -= step;
			}
			break;
		// вверх
		case Up:
			if (_startPosY - step > _cabinHeight) {
				_startPosY -= step;
			}

			break;
		// вниз
		case Down:
			if (_startPosY + step < _pictureHeight - trackedVehicleHeight) {
				_startPosY += step;
			}
			break;
		}
	}

	@Override
	public void drawCrane(Graphics g) {
		// гусеничная машина
		g.setColor(mainColor);
		g.fillRect(_startPosX + 70, _startPosY, 130, 50);
		g.fillRect(_startPosX + 20, _startPosY - 20, 50, 70);

		g.setColor(Color.blue);
		g.fillRect(_startPosX + 30, _startPosY - 10, 30, 40);

		g.setColor(Color.black);
		g.drawRect(_startPosX + 70, _startPosY, 130, 50);
		g.fillRect(_startPosX + 70, _startPosY + 50, 50, 10);
		g.drawRect(_startPosX + 70, _startPosY + 50, 50, 10);
		g.drawRect(_startPosX + 20, _startPosY - 20, 50, 70);
		g.drawRect(_startPosX + 30, _startPosY - 10, 30, 40);

		Polygon points = new Polygon();
		points.addPoint(_startPosX, _startPosY + 70);
		points.addPoint(_startPosX, _startPosY + 110);
		points.addPoint(_startPosX + 190, _startPosY + 110);
		points.addPoint(_startPosX + 190, _startPosY + 70);
		points.addPoint(_startPosX + 170, _startPosY + 60);
		points.addPoint(_startPosX + 20, _startPosY + 60);
		g.fillPolygon(points);

		g.setColor(Color.darkGray);
		if (isBaseRinks) {
			for (int i = 0; i < 4; i++) {
				g.fillOval(_startPosX + 47 * i, _startPosY + 63, 47, 47);
			}
		}
	}

	@Override
	public String toString() {
		return maxSpeed + separator + weight + separator + mainColor.getRGB();
	}

	public boolean equals(TrackedVehicle other) {
		if (other == null) {
			return false;
		}
		if (this.getClass().getName() != other.getClass().getName()) {
			return false;
		}
		if (maxSpeed != other.maxSpeed) {
			return false;
		}
		if (weight != other.weight) {
			return false;
		}
		if (mainColor != other.mainColor) {
			return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TrackedVehicle)) {
			return false;
		} else {
			return equals((TrackedVehicle) obj);
		}
	}

	@Override
	public int compareTo(TrackedVehicle other) {
		if (maxSpeed != other.maxSpeed) {
			return maxSpeed - other.maxSpeed;
		}
		if (weight != other.weight) {
			return (int) (weight - other.weight);
		}
		if (mainColor.getRGB() != other.mainColor.getRGB()) {
			return mainColor.getRGB() - other.mainColor.getRGB();
		}
		return 0;
	}
}
