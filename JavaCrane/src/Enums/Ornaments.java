package Enums;

public enum Ornaments {
	Ornament1, Ornament2;
	public static Ornaments ornamentKind(int kol) {
		switch (kol) {
		case 1:
			return Ornaments.Ornament1;
		case 2:
			return Ornaments.Ornament2;
		}
		return null;
	}
}