package by.vsu.mf.amm.sta.console.visualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import by.vsu.mf.amm.sta.console.visualizer.example.MatrixMultiplicationProblemVisualizer;
import by.vsu.mf.amm.sta.console.visualizer.example.operation.CalculateScalarProductOperationVisualizer;
import by.vsu.mf.amm.sta.console.visualizer.example.operation.CreateEmptyResultMatrixOperationVisualizer;
import by.vsu.mf.amm.sta.method.Method;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.method.operation.example.CalculateScalarProductOperation;
import by.vsu.mf.amm.sta.method.operation.example.CreateEmptyResultMatrixOperation;
import by.vsu.mf.amm.sta.problem.Problem;
import by.vsu.mf.amm.sta.problem.example.MatrixMultiplicationProblem;

public class VisualizerContainer {
	private static Map<Class<? extends Problem>, ProblemVisualizer<?, ?>> problemsVisualizers = new HashMap<>();

	static {
		problemsVisualizers.put(MatrixMultiplicationProblem.class, new MatrixMultiplicationProblemVisualizer());
	}

	private static Map<Class<? extends Operation<?, ?>>, OperationVisualizer<?>> operationsVisualizers = new HashMap<>();

	static {
		operationsVisualizers.put(CalculateScalarProductOperation.class, new CalculateScalarProductOperationVisualizer());
		operationsVisualizers.put(CreateEmptyResultMatrixOperation.class, new CreateEmptyResultMatrixOperationVisualizer());
	}

	@SuppressWarnings("unchecked")
	public static <P extends Problem, M extends Method<P>> ProblemVisualizer<P, M> getProblemVisualizer(Class<P> problemClass, Class<M> methodClass) {
		return (ProblemVisualizer<P, M>)problemsVisualizers.get(problemClass);
	}

	@SuppressWarnings("unchecked")
	public static <P extends Problem, O extends Operation<P, Method<P>>> OperationVisualizer<O> getOperationVisualizer(Class<P> problemClass, Class<O> operationClass, Scanner scanner) {
		OperationVisualizer<O> visualizer = (OperationVisualizer<O>)operationsVisualizers.get(operationClass);
		visualizer.setScanner(scanner);
		return visualizer;
	}
}
