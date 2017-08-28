package conan.weiax.exception;

public class GlobalException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4059059251423134443L;
	private String message;

	public GlobalException() {
		super();
	}

	public GlobalException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
