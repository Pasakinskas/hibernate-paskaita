package eu.codeacademy.exception;

public class DataNotFoundException extends RuntimeException {

	public DataNotFoundException() {
		super("DB data not found by criteria!");
	}
}
