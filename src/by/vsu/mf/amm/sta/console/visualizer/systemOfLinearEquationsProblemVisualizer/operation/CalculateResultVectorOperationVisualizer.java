package by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizer.operation;

import java.util.InputMismatchException;
import java.util.Scanner;

import by.vsu.mf.amm.sta.console.visualizer.OperationVisualizer;
import by.vsu.mf.amm.sta.exception.visualization.VisualizationException;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.tridiagonalMatrixAlgorithmOperations.CalculateResultVectorOperation;

public class CalculateResultVectorOperationVisualizer extends OperationVisualizer<CalculateResultVectorOperation> {

    @Override
    public void input(CalculateResultVectorOperation operation) throws VisualizationException {
        Scanner scanner = getScanner();
        System.out.println("В рамках данной операции нужно выбрать:");
        System.out.println("номер вычисляемого неизвестного ");
        System.out.println("и найти его;");
        System.out.println();

        System.out.print("Введите номер коэффициентов A и B >");

        operation.setIndex(getVectorIndex(scanner));

    }

    private Integer getVectorIndex(Scanner scanner) throws VisualizationException {
        try {
            return scanner.nextInt() - 1;
        } catch (InputMismatchException e) {
            System.out.printf("Номер должен быть натуральным числом\n");
            throw new VisualizationException(e);
        }
    }

}
