package by.vsu.mf.amm.sta.console.visualizer;

import java.util.Scanner;

import by.vsu.mf.amm.sta.exception.visualization.VisualizationException;
import by.vsu.mf.amm.sta.method.operation.Operation;

abstract public class OperationVisualizer<O extends Operation<?, ?>> {
	private Scanner scanner;

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	abstract public void input(O operation) throws VisualizationException;
}
