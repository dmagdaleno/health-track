package br.com.healthtech.healthtrack.exception;

public class DBException extends Exception {

	/**
	 * Default serial version UID 
	 */
	private static final long serialVersionUID = 1L;

	public DBException() {
		super();
	}
	
	public DBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public DBException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DBException(String message) {
		super(message);
	}
	
	public DBException(Throwable cause) {
		super(cause);
	}
}
