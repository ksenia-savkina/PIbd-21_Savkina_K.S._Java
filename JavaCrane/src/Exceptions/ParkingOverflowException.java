package Exceptions;

public class ParkingOverflowException extends Exception {
	public ParkingOverflowException() {
		super("На стоянке нет свободных мест");
	}
}
