package Exceptions;

public class CraneNotFoundException extends Exception {
	public CraneNotFoundException(int i) {
		super("Не найден кран по месту " + i);
	}
}
