package HoistingCranePckg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class HoistingCrane {
	// ����� ���������� ��������� ���������� ������
	private int _startPosX;
	// ������ ���������� ��������� ���������� ������
	private int _startPosY;
	// ������ ���� ���������
	private int _pictureWidth;
	// ������ ���� ���������
	private int _pictureHeight;
	// ������ ��������� ���������� ������
	final int trackedVehicleWidth = 200;
	// ������ ��������� ���������� ������
	final int trackedVehicleHeight = 110;
	// ������ ��������� ������
	final int _arrowHeight = 230;
	// ������ ��������� ������
	final int _cabinHeight = 20;
	// ������ ��������� �����������
	final int _counterweightHeight = 70;
	// ������ ��������� �����������
	final int _counterweightWidth = 30;
	// �������� ����
	public Color mainColor;
	// �������������� ����
	public Color dopColor;
	// ������������ �������� ���������� ������
	public int maxSpeed;
	// ����� ����� ��������
	public float weight;
	// ������� ������� ������
	public boolean arrow;
	// ������� ������� �����������
	public boolean counterweight;

	private Rink rink;

	// �����������
	// "maxSpeed" ������������ ��������(�/���)
	// "weight" ����� ����� ��������(�)
	// "mainColor" �������� ����
	// "dopColor" �������������� ����

	// "cabin" ������� ������� ������
	// "arrow" ������� ������� ������
	// "counterweight" ������� ������� �����������(�)
	public HoistingCrane(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean arrow,
			boolean counterweight, int count) {
		this.maxSpeed = maxSpeed;
		this.weight = weight;
		this.mainColor = mainColor;
		this.dopColor = dopColor;
		this.arrow = arrow;
		this.counterweight = counterweight;
		rink = new Rink(count);
	}

	// ��������� ������� ���������� �����
	// "x" ���������� X
	// "y" ���������� Y
	// "width" ������ ��������
	// "height" ������ ��������
	public void setPosition(int x, int y, int width, int height) {
		_pictureHeight = height;
		_pictureWidth = width;
		if (arrow || counterweight) {
			_startPosX = x;
			if (arrow || arrow && counterweight) {
				_startPosY = y + _arrowHeight;
			} else {
				_startPosY = y + _counterweightHeight;
			}
		} else {
			_startPosX = x;
			_startPosY = y;
		}
	}

	// ��������� ����������� �����������
	public void moveCrane(Direction direction) {
		float step = maxSpeed * 100 / weight;
		switch (direction) {
		// ������
		case Right:
			if (counterweight) {
				if (_startPosX + step < _pictureWidth - (trackedVehicleWidth + _counterweightWidth)) {
					_startPosX += step;
				}
			} else {
				if (_startPosX + step < _pictureWidth - trackedVehicleWidth) {
					_startPosX += step;
				}
			}
			break;
		// �����
		case Left:
			if (_startPosX - step > 0) {
				_startPosX -= step;
			}
			break;
		// �����
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
				if (_startPosY - step > _cabinHeight) {
					_startPosY -= step;
				}
			}
			break;
		// ����
		case Down:
			if (_startPosY + step < _pictureHeight - trackedVehicleHeight) {
				_startPosY += step;
			}
			break;
		}
	}

	// ���������
	public void drawCrane(Graphics g) {
		// ���������� ������
		g.setColor(mainColor);
		g.fillRect(_startPosX + 70, _startPosY, 130, 50);

		g.setColor(Color.black);
		g.drawRect(_startPosX + 70, _startPosY, 130, 50);
		g.fillRect(_startPosX + 70, _startPosY + 50, 50, 10);
		g.drawRect(_startPosX + 70, _startPosY + 50, 50, 10);

		Polygon points = new Polygon();
		points.addPoint(_startPosX, _startPosY + 70);
		points.addPoint(_startPosX, _startPosY + 110);
		points.addPoint(_startPosX + 190, _startPosY + 110);
		points.addPoint(_startPosX + 190, _startPosY + 70);
		points.addPoint(_startPosX + 170, _startPosY + 60);
		points.addPoint(_startPosX + 20, _startPosY + 60);
		g.fillPolygon(points);

		rink.drawRink(_startPosX, _startPosY + 63, g, dopColor);

		g.drawRect(_startPosX + 20, _startPosY - 20, 50, 70);
		g.drawRect(_startPosX + 30, _startPosY - 10, 30, 40);
		g.setColor(mainColor);
		g.fillRect(_startPosX + 20, _startPosY - 20, 50, 70);
		g.setColor(Color.blue);
		g.fillRect(_startPosX + 30, _startPosY - 10, 30, 40);

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
