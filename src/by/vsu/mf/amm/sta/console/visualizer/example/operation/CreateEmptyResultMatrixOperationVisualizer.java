package by.vsu.mf.amm.sta.console.visualizer.example.operation;

import java.util.InputMismatchException;
import java.util.Scanner;

import by.vsu.mf.amm.sta.console.visualizer.OperationVisualizer;
import by.vsu.mf.amm.sta.exception.visualization.VisualizationException;
import by.vsu.mf.amm.sta.method.operation.example.CreateEmptyResultMatrixOperation;

public class CreateEmptyResultMatrixOperationVisualizer extends OperationVisualizer<CreateEmptyResultMatrixOperation> {
	@Override
	public void input(CreateEmptyResultMatrixOperation operation) throws VisualizationException {
		Scanner scanner = getScanner();
		System.out.println();
		System.out.print("Введите количество строк матрицы-результата > ");
		operation.setRowsQuantity(getDimention(scanner));
		System.out.println();
		System.out.print("Введите количество столбцов матрицы-результата > ");
		operation.setColumnsQuantity(getDimention(scanner));
	}

	private Integer getDimention(Scanner scanner) throws VisualizationException {
		try {
			return scanner.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Размернось должна быть натуральным числом");
			throw new VisualizationException(e);
		}
	}
}
