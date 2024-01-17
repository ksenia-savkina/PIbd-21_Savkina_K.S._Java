package Exceptions;

public class ParkingAlreadyHaveException extends Exception {
	public ParkingAlreadyHaveException() {
		super("На стоянке уже есть такой кран");
	}
}