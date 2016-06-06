package by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizer.operation;

import java.util.InputMismatchException;
import java.util.Scanner;

import by.vsu.mf.amm.sta.console.visualizer.OperationVisualizer;
import by.vsu.mf.amm.sta.exception.visualization.VisualizationException;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.tridiagonalMatrixAlgorithmOperations.CreateEmptyResultVectorOperation;

public class CreateEmptyResultVectorOperationVisualizer extends OperationVisualizer<CreateEmptyResultVectorOperation> {

    @Override
    public void input(CreateEmptyResultVectorOperation operation) throws VisualizationException {
        Scanner scanner = getScanner();
        System.out.println();
        System.out.println("Проверьте удовлетворяет ли условие требованиям метода прогонки.");
        System.out.println("Если удовлетворяет введите размерность вектора решений.");
        System.out.println("Если не удовлетворяет введите 0  > ");
        operation.setQuantity(getDimention(scanner));
        System.out.println();

    }

    private Integer getDimention(Scanner scanner) throws VisualizationException {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Размернось должна быть натуральным числом");
            throw new VisualizationException(e);

        }
    }
}
