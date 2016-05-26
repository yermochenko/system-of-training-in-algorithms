package by.vsu.mf.amm.sta.console.visualizer.example.operation;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import by.vsu.mf.amm.sta.console.visualizer.OperationVisualizer;
import by.vsu.mf.amm.sta.exception.visualization.VisualizationException;
import by.vsu.mf.amm.sta.method.operation.example.CalculateScalarProductOperation;
import by.vsu.mf.amm.sta.method.operation.example.CalculateScalarProductOperation.VectorFromMatrixType;

public class CalculateScalarProductOperationVisualizer extends OperationVisualizer<CalculateScalarProductOperation> {
	@Override
	public void input(CalculateScalarProductOperation operation) throws VisualizationException {
		Scanner scanner = getScanner();
		System.out.println("В рамках данной операции нужно выбрать:");
		System.out.println("\tа) строку или столбец из матриц-операндов;");
		System.out.println("\tб) найти их скалярное произведение;");
		System.out.println("\tв) занести полученное число в необходимый элемент матрицы-результата.");
		System.out.println();
		System.out.print("Для первой матрицы-опранда ");
		VectorFromMatrixType firstVectorFromMatrixType = getVectorFromMatrixType(scanner);
		operation.setFirstVectorFromMatrixType(firstVectorFromMatrixType);
		System.out.println();
		System.out.print("Теперь ");
		operation.setFirstVectorIndex(getVectorIndex(scanner, firstVectorFromMatrixType));
		System.out.println();
		System.out.print("Для второй матрицы-опранда ");
		VectorFromMatrixType secondVectorFromMatrixType = getVectorFromMatrixType(scanner);
		operation.setSecondVectorFromMatrixType(secondVectorFromMatrixType);
		System.out.println();
		System.out.print("Теперь ");
		operation.setSecondVectorIndex(getVectorIndex(scanner, secondVectorFromMatrixType));
		System.out.println();
		System.out.print("Для матрицы результата ");
		operation.setResultRowIndex(getVectorIndex(scanner, VectorFromMatrixType.ROW));
		System.out.println();
		System.out.print("Для матрицы результата ");
		operation.setResultColumnIndex(getVectorIndex(scanner, VectorFromMatrixType.COLUMN));
	}

	private static Map<VectorFromMatrixType, String> vectorFromMatrixTypes = new HashMap<>();

	static {
		vectorFromMatrixTypes.put(VectorFromMatrixType.ROW, "строки матрицы");
		vectorFromMatrixTypes.put(VectorFromMatrixType.COLUMN, "столбца матрицы");
	}

	private Integer getVectorIndex(Scanner scanner, VectorFromMatrixType type) throws VisualizationException {
		try {
			System.out.printf("введите номер %s > ", vectorFromMatrixTypes.get(type));
			return scanner.nextInt() - 1;
		} catch(InputMismatchException e) {
			System.out.printf("Номер %s должен быть натуральным числом\n", vectorFromMatrixTypes.get(type));
			throw new VisualizationException(e);
		}
	}

	private VectorFromMatrixType getVectorFromMatrixType(Scanner scanner) throws VisualizationException {
		try {
			VectorFromMatrixType types[] = VectorFromMatrixType.values();
			System.out.println("выберите что, строку или столбец матрицы, нужно использовать:");
			for(int index = 0; index < types.length; index++) {
				System.out.printf("\t%d.\tвыбор %s\n", index + 1, vectorFromMatrixTypes.get(types[index]));
			}
			System.out.println();
			System.out.print("Введите номер пункта > ");
			return types[scanner.nextInt() - 1];
		} catch(InputMismatchException e) {
			System.out.println("Номер пункта должен быть натуральным числом");
			throw new VisualizationException(e);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Пункта с таким номером нет в списке");
			throw new VisualizationException(e);
		}
	}
}
