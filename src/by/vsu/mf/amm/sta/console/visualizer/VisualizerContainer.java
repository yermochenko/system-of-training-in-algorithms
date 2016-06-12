package by.vsu.mf.amm.sta.console.visualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import by.vsu.mf.amm.sta.console.visualizer.example.MatrixMultiplicationProblemVisualizer;
import by.vsu.mf.amm.sta.console.visualizer.example.operation.CalculateScalarProductOperationVisualizer;
import by.vsu.mf.amm.sta.console.visualizer.example.operation.CreateEmptyResultMatrixOperationVisualizer;
import by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizer.SystemOfLinearEquationsProblemVisualizer;
import by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizer.operation.CalculateMethodCoefficientsOperationVisualizer;
import by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizer.operation.CalculateResultVectorOperationVisualizer;
import by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizer.operation.CreateEmptyResultVectorOperationVisualizer;
import by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizerSeidelMethod.SystemOfLinearEquationsProblemVisualizerSeidelMethod;
import by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizerSeidelMethod.operation.CalculateSeidelMethodOperationVisualizer;
import by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizerSeidelMethod.operation.CreateEmptyResultSeldelVectorOperationVisualizer;
import by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizerSimpleIteration.SystemOfLinearEquationsProblemVisualizerSimpleIteration;
import by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizerSimpleIteration.operation.CalculateSimpleIterationOperationVisualizer;
import by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizerSimpleIteration.operation.CreateEmptyResultSimpleIterationVectorOperationVisualizer;
import by.vsu.mf.amm.sta.method.Method;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.method.operation.example.CalculateScalarProductOperation;
import by.vsu.mf.amm.sta.method.operation.example.CreateEmptyResultMatrixOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.tridiagonalMatrixAlgorithmOperations.CalculateMethodCoefficientsOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.tridiagonalMatrixAlgorithmOperations.CalculateResultVectorOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.tridiagonalMatrixAlgorithmOperations.CreateEmptyResultVectorOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.seidelAlgorithmOperations.CalculateSeidelMethodOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.seidelAlgorithmOperations.CreateEmptyResultSeidelVectorOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.simpleIterationAlgorithmOperations.CalculateSimpleIterationOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.simpleIterationAlgorithmOperations.CreateEmptyResultSimpleIterationVectorOperation;
import by.vsu.mf.amm.sta.problem.Problem;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblem;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblemSeidelMethod;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblemSimpleIterationMethod;
import by.vsu.mf.amm.sta.problem.example.MatrixMultiplicationProblem;

public class VisualizerContainer {
    private static Map<Class<? extends Problem>, ProblemVisualizer<?, ?>> problemsVisualizers = new HashMap<>();

    static {
        problemsVisualizers.put(MatrixMultiplicationProblem.class, new MatrixMultiplicationProblemVisualizer());
        problemsVisualizers.put(SystemOfLinearEquationsProblem.class, new SystemOfLinearEquationsProblemVisualizer());
        problemsVisualizers.put(SystemOfLinearEquationsProblemSeidelMethod.class, new SystemOfLinearEquationsProblemVisualizerSeidelMethod());
        problemsVisualizers.put(SystemOfLinearEquationsProblemSimpleIterationMethod.class, new SystemOfLinearEquationsProblemVisualizerSimpleIteration());
         /*
         * bug одинаковых проблем в problemsVisualizers
         * 
         * При добавлении в problemsVisualizers одинаковых проблем, но разных методов ее решения - появляется ошибка.
         * Предыдущие методы не работают, а выкидывают ошибку.
         * Последний метод будет работать без каких-либо ошибок.
         */
    }

    private static Map<Class<? extends Operation<?, ?>>, OperationVisualizer<?>> operationsVisualizers = new HashMap<>();

    static {
        operationsVisualizers.put(CalculateScalarProductOperation.class, new CalculateScalarProductOperationVisualizer());
        operationsVisualizers.put(CreateEmptyResultMatrixOperation.class, new CreateEmptyResultMatrixOperationVisualizer());

        operationsVisualizers.put(CreateEmptyResultVectorOperation.class, new CreateEmptyResultVectorOperationVisualizer());
        operationsVisualizers.put(CalculateMethodCoefficientsOperation.class, new CalculateMethodCoefficientsOperationVisualizer());
        operationsVisualizers.put(CalculateResultVectorOperation.class, new CalculateResultVectorOperationVisualizer());

        operationsVisualizers.put(CreateEmptyResultSeidelVectorOperation.class,  new CreateEmptyResultSeldelVectorOperationVisualizer());
        operationsVisualizers.put(CalculateSeidelMethodOperation.class, new CalculateSeidelMethodOperationVisualizer());
        
        operationsVisualizers.put(CreateEmptyResultSimpleIterationVectorOperation.class,  new CreateEmptyResultSimpleIterationVectorOperationVisualizer());
        operationsVisualizers.put(CalculateSimpleIterationOperation.class, new CalculateSimpleIterationOperationVisualizer());
    }

    @SuppressWarnings("unchecked")
    public static <P extends Problem, M extends Method<P>> ProblemVisualizer<P, M> getProblemVisualizer(
            Class<P> problemClass, Class<M> methodClass) {
        return (ProblemVisualizer<P, M>) problemsVisualizers.get(problemClass);
    }

    @SuppressWarnings("unchecked")
    public static <P extends Problem, O extends Operation<P, Method<P>>> OperationVisualizer<O> getOperationVisualizer(
            Class<P> problemClass, Class<O> operationClass, Scanner scanner) {
        OperationVisualizer<O> visualizer = (OperationVisualizer<O>) operationsVisualizers.get(operationClass);
        visualizer.setScanner(scanner);
        return visualizer;
    }
}
