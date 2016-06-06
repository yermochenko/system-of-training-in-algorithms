package by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizer.operation;

import java.util.InputMismatchException;
import java.util.Scanner;

import by.vsu.mf.amm.sta.console.visualizer.OperationVisualizer;
import by.vsu.mf.amm.sta.exception.visualization.VisualizationException;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.tridiagonalMatrixAlgorithmOperations.CalculateMethodCoefficientsOperation;

public class CalculateMethodCoefficientsOperationVisualizer extends OperationVisualizer<CalculateMethodCoefficientsOperation> {

    @Override
    public void input(CalculateMethodCoefficientsOperation operation) throws VisualizationException {
        Scanner scanner = getScanner();
        System.out.println("В рамках данной операции нужно выбрать:");
        System.out.println("номера вычисляемых коэффициентов алгоритма;");
        System.out.println("и найти их;");
        System.out.println();

        System.out.print("Введите номер коэффициентов A и B >");

        operation.setCoefficientsIndex(getVectorIndex(scanner));
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
