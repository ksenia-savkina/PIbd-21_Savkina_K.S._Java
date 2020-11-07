package HoistingCranePckg;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import Interfaces.IRink;

public class ParkingCollection {

	// Словарь (хранилище) со стоянками
	private final Map<String, Parking<Platform, IRink>> parkingStages;
	// Ширина окна отрисовки
	private final int pictureWidth;
	// Высота окна отрисовки
	private final int pictureHeight;

	// Конструктор
	public ParkingCollection(int pictureWidth, int pictureHeight) {
		parkingStages = new HashMap<>();
		this.pictureWidth = pictureWidth;
		this.pictureHeight = pictureHeight;
	}

	public Set<String> keySet() {
		return parkingStages.keySet();
	}

	// Добавление стоянки
	public void addParking(String name) {
		if (parkingStages.containsKey(name)) {
			return;
		}
		parkingStages.put(name, new Parking<>(pictureWidth, pictureHeight));
	}

	// Удаление стоянки
	public void delParking(String name) {
		if (parkingStages.containsKey(name)) {
			parkingStages.remove(name);
		}
	}

	// Доступ к стоянке
	public Parking<Platform, IRink> get(String name) {
		if (parkingStages.containsKey(name)) {
			return parkingStages.get(name);
		}
		return null;
	}

	public Platform get(String name, int index) {
		if (parkingStages.containsKey(name)) {
			return parkingStages.get(name).get(index);
		}
		return null;
	}
}
