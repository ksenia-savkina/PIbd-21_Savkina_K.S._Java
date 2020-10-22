package Enums;

public enum Figures {
	Circle, Square;
	public static Figures figureKind(int kol) {
		switch (kol) {
		case 1:
			return Figures.Circle;
		case 2:
			return Figures.Square;
		}
		return null;
	}
}
	
