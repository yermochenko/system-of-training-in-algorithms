package by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizerSeidelMethod.operation;

import java.util.InputMismatchException;
import java.util.Scanner;

import by.vsu.mf.amm.sta.console.visualizer.OperationVisualizer;
import by.vsu.mf.amm.sta.exception.visualization.VisualizationException;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.seidelAlgorithmOperations.CalculateSeidelMethodOperation;

public class CalculateSeidelMethodOperationVisualizer extends OperationVisualizer<CalculateSeidelMethodOperation> {

    @Override
    public void input(CalculateSeidelMethodOperation operation) throws VisualizationException {
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
