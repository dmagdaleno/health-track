package br.com.healthtech.healthtrack.exception;

public class LoginInvalidoException extends Exception {
	/**
	 * Default serial version UID 
	 */
	private static final long serialVersionUID = 1L;

	public LoginInvalidoException() {
		super();
	}
	
	public LoginInvalidoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public LoginInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public LoginInvalidoException(String message) {
		super(message);
	}
	
	public LoginInvalidoException(Throwable cause) {
		super(cause);
	}
}
