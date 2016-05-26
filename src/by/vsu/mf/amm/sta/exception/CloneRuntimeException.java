package by.vsu.mf.amm.sta.exception;

public class CloneRuntimeException extends RuntimeException {
	public CloneRuntimeException() {}

	public CloneRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CloneRuntimeException(String message) {
		super(message);
	}

	public CloneRuntimeException(Throwable cause) {
		super(cause);
	}
}
