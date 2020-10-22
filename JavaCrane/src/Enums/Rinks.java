package HoistingCranePckg;

public enum Rinks {
	Four, Five, Six;
	public static Rinks countRinks(int kol) {
		switch (kol) {
		case 4:
			return Rinks.Four;
		case 5:
			return Rinks.Five;
		case 6:
			return Rinks.Six;
		}
		return null;
	}
}
