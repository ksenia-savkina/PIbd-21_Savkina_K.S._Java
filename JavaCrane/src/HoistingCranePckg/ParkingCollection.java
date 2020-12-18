package HoistingCranePckg;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import Exceptions.ParkingOverflowException;
import Interfaces.ICrane;
import Interfaces.IRink;

public class ParkingCollection {

	// Словарь (хранилище) со стоянками
	private final Map<String, Parking<Platform, IRink>> parkingStages;
	// Ширина окна отрисовки
	private final int pictureWidth;
	// Высота окна отрисовки
	private final int pictureHeight;

	private final String separator = ":";

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

	public void saveData(String filename) throws IOException {
		FileWriter fw = new FileWriter(filename);
		fw.write("ParkingCollection\n");
		for (String level : parkingStages.keySet()) {
			fw.write("Parking" + separator + level + "\n");
			ICrane crane = null;
			for (int i = 0; (crane = parkingStages.get(level).get(i)) != null; i++) {
				if (crane.getClass().toString().equals("class HoistingCranePckg.TrackedVehicle")) {
					fw.write("TrackedVehicle" + separator);
				}
				if (crane.getClass().toString().equals("class HoistingCranePckg.HoistingCrane")) {
					fw.write("HoistingCrane" + separator);
				}
				fw.write(crane + "\n");
			}
		}
		fw.close();
	}

	public void loadData(String filename) throws IOException, ParkingOverflowException {
		FileReader fr = new FileReader(filename);
		Scanner scanner = new Scanner(fr);
		String line;
		String key = "";
		if ((line = scanner.nextLine()).contains("ParkingCollection")) {
			parkingStages.clear();
			Platform crane = null;
			if (scanner.hasNextLine()) {
				line = scanner.nextLine();
			} else {
				line = null;
			}
			while (line != null) {
				if (line.contains("Parking")) {
					key = line.split(separator)[1];
					parkingStages.put(key, new Parking<Platform, IRink>(pictureWidth, pictureHeight));
					if (scanner.hasNextLine()) {
						line = scanner.nextLine();
					} else {
						break;
					}
					continue;
				}
				if (line.contains("TrackedVehicle")) {
					crane = new TrackedVehicle(line.split(separator)[1]);
				} else if (line.contains("HoistingCrane")) {
					crane = new HoistingCrane(line.split(separator)[1]);
				}
				var result = parkingStages.get(key).addition(crane);
				if (!result) {
					throw new NullPointerException();
				}
				if (scanner.hasNextLine()) {
					line = scanner.nextLine();
				} else {
					break;
				}
			}
			fr.close();
		} else {
			throw new FileNotFoundException();
		}
	}

	public void saveSeparateParking(String filename, String name) throws IOException {
		if (parkingStages.containsKey(name)) {
			FileWriter fw = new FileWriter(filename);
			fw.write("ParkingCollection\n");
			Parking<Platform, IRink> level = parkingStages.get(name);
			fw.write("Parking" + separator + name + "\n");
			ICrane crane = null;
			for (int i = 0; (crane = level.get(i)) != null; i++) {
				if (crane.getClass().toString().equals("class HoistingCranePckg.TrackedVehicle")) {
					fw.write("TrackedVehicle" + separator);
				}
				if (crane.getClass().toString().equals("class HoistingCranePckg.HoistingCrane")) {
					fw.write("HoistingCrane" + separator);
				}
				fw.write(crane + "\n");
			}
			fw.close();
		} else {
			 throw new NullPointerException();
		}
	}

	public void loadSeparateParking(String filename) throws IOException, ParkingOverflowException {
		FileReader fr = new FileReader(filename);
		Scanner scanner = new Scanner(fr);
		String line;
		String key = "";
		Platform crane = null;
		if ((line = scanner.nextLine()).contains("ParkingCollection")) {
			if (scanner.hasNextLine()) {
				line = scanner.nextLine();
			} else {
				line = null;
			}
			while (line != null) {
				if (line.contains("Parking")) {
					key = line.split(separator)[1];
					if (parkingStages.containsKey(key)) {
						parkingStages.get(key).clear();
					} else {
						parkingStages.put(key, new Parking<Platform, IRink>(pictureWidth, pictureHeight));
					}
					if (scanner.hasNextLine()) {
						line = scanner.nextLine();
					} else {
						break;
					}
					continue;
				}
				if (line.contains("TrackedVehicle")) {
					crane = new TrackedVehicle(line.split(separator)[1]);
				} else if (line.contains("HoistingCrane")) {
					crane = new HoistingCrane(line.split(separator)[1]);
				}
				var result = parkingStages.get(key).addition(crane);
				if (!result) {
					throw new NullPointerException();
				}
				if (scanner.hasNextLine()) {
					line = scanner.nextLine();
				} else {
					break;
				}
			}
			fr.close();
		} else {
			throw new FileNotFoundException();
		}
	}
}
