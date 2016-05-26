package by.vsu.mf.amm.sta.exception.visualization;

public class VisualizationException extends Exception {
	public VisualizationException() {}

	public VisualizationException(String message, Throwable exception) {
		super(message, exception);
	}

	public VisualizationException(String message) {
		super(message);
	}

	public VisualizationException(Throwable exception) {
		super(exception);
	}
}
