package by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizerSimpleIteration.operation;

import java.util.InputMismatchException;
import java.util.Scanner;

import by.vsu.mf.amm.sta.console.visualizer.OperationVisualizer;
import by.vsu.mf.amm.sta.exception.visualization.VisualizationException;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.simpleIterationAlgorithmOperations.CalculateSimpleIterationOperation;

public class CalculateSimpleIterationOperationVisualizer extends OperationVisualizer<CalculateSimpleIterationOperation> {

    @Override
    public void input(CalculateSimpleIterationOperation operation) throws VisualizationException {
        Scanner scanner = getScanner();
        System.out.println("В рамках данной операции нужно :");
        System.out.println();
        System.out.println("Введите точность (количество чисел после запятой) ");
        operation.setAccuracy(getNumber(scanner));

    }

    private Integer getNumber(Scanner scanner) throws VisualizationException {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.printf("Чисело должно быть натуральным \n");
            throw new VisualizationException(e);
        }
    }
}
