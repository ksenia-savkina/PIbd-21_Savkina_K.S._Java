package HoistingCranePckg;

import Interfaces.ICrane;

import java.util.Comparator;

public class CraneComparer implements Comparator<ICrane> {

    public int compare(ICrane x, ICrane y) {
        if (x instanceof HoistingCrane && y instanceof HoistingCrane) {
            return comparerHoistingCrane((HoistingCrane) x, (HoistingCrane) y);
        }
        if (x instanceof TrackedVehicle && y instanceof HoistingCrane) {
            return 1;
        }
        if (x instanceof HoistingCrane && y instanceof TrackedVehicle) {
            return -1;
        }
        if (x instanceof TrackedVehicle && y instanceof TrackedVehicle) {
            return comparerTrackedVehicle((TrackedVehicle) x, (TrackedVehicle) y);
        }
        return 0;
    }

    private int comparerTrackedVehicle(TrackedVehicle x, TrackedVehicle y) {
        if (x.maxSpeed != y.maxSpeed) {
            return x.maxSpeed - y.maxSpeed;
        }
        if (x.weight != y.weight) {
            return (int) (x.weight - y.weight);
        }
        if (x.mainColor != y.mainColor) {
            return x.mainColor.getRGB() - y.mainColor.getRGB();
        }
        return 0;
    }

    private int comparerHoistingCrane(HoistingCrane x, HoistingCrane y) {
        var res = comparerTrackedVehicle(x, y);
        if (res != 0) {
            return res;
        }
        if (x.dopColor != y.dopColor) {
            return x.dopColor.getRGB() - y.dopColor.getRGB();
        }
        if (x.arrow != y.arrow) {
            if (x.arrow) {
                return 1;
            }
            return -1;
        }
        if (x.counterweight != y.counterweight) {
            if (x.counterweight) {
                return 1;
            }
            return -1;
        }
        if (x.getDop() != y.getDop()) {
            int firstCraneCountID = 0;
            int secondCraneCountID = 0;
            switch (x.getDop()) {
                case "Обыкновенные катки":
                    firstCraneCountID = 1;
                    break;
                case "Круги на катках":
                    firstCraneCountID = 2;
                    break;
                case "Орнамент №1 на катках":
                    firstCraneCountID = 3;
                    break;
                case "Орнамент №2 на катках":
                    firstCraneCountID = 4;
                    break;
            }
            switch (y.getDop()) {
                case "Обыкновенные катки":
                    secondCraneCountID = 1;
                    break;
                case "Круги на катках":
                    secondCraneCountID = 2;
                    break;
                case "Орнамент №1 на катках":
                    secondCraneCountID = 3;
                    break;
                case "Орнамент №2 на катках":
                    secondCraneCountID = 4;
                    break;
            }
            return firstCraneCountID - secondCraneCountID;
        }
        if (x.getCount() != y.getCount()) {
            return x.getCount() - y.getCount();
        }
        return 0;
    }
}
