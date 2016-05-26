package by.vsu.mf.amm.sta.ioc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import by.vsu.mf.amm.sta.method.Method;
import by.vsu.mf.amm.sta.method.example.MatrixMultiplicationMethod;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.method.operation.example.CalculateScalarProductOperation;
import by.vsu.mf.amm.sta.method.operation.example.CreateEmptyResultMatrixOperation;
import by.vsu.mf.amm.sta.problem.Problem;
import by.vsu.mf.amm.sta.problem.example.MatrixMultiplicationProblem;
import by.vsu.mf.amm.sta.problem.generator.ProblemGenerator;

public class Container {
	private static Map<Wrapper<? extends Problem>, Map<Wrapper<? extends Method<?>>, List<Wrapper<? extends Operation<?, ?>>>>> problems;
	private static Map<Class<? extends Problem>, Map<Class<? extends Method<?>>, ProblemGenerator<?, ?>>> generators;

	static {
		List<Wrapper<? extends Operation<?, ?>>> operations;
		Map<Wrapper<? extends Method<?>>, List<Wrapper<? extends Operation<?, ?>>>> methods;
		problems = new LinkedHashMap<>();
		generators = new LinkedHashMap<>();
		operations = new ArrayList<>();
		operations.add(new Wrapper<>(CreateEmptyResultMatrixOperation.class, "Создание матрицы результата указанного размера (или размера 0 на 0, если результат вычислен быть не может)"));
		operations.add(new Wrapper<>(CalculateScalarProductOperation.class, "Вычисление скалярного произведения двух векторов и запись значения в результирующую матрицу"));
		methods = new LinkedHashMap<>();
		methods.put(new Wrapper<>(MatrixMultiplicationMethod.class, "Метод непосредственного умножения двух прямоугольных матриц"), operations);
		problems.put(new Wrapper<>(MatrixMultiplicationProblem.class, "Задача перемножения двух матриц"), methods);
	}

	public static List<Wrapper<? extends Problem>> getProblems() {
		return new ArrayList<>(problems.keySet());
	}

	public static List<Wrapper<? extends Method<?>>> getMethods(Class<? extends Problem> problemClass) {
		return new ArrayList<>(problems.get(new Wrapper<>(problemClass, null)).keySet());
	}

	public static List<Wrapper<? extends Operation<?, ?>>> getOperations(Class<? extends Problem> problemClass, @SuppressWarnings("rawtypes") Class<? extends Method> methodClass) {
		Map<Wrapper<? extends Method<?>>, List<Wrapper<? extends Operation<?, ?>>>> methods = problems.get(new Wrapper<>(problemClass, null));
		if(methods != null) {
			return methods.get(new Wrapper<>(methodClass, null));
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <P extends Problem, M extends Method<P>> ProblemGenerator<P, M> getGenerator(Class<P> problemClass, Class<M> methodClass) {
		Map<Class<? extends Method<?>>, ProblemGenerator<?, ?>> generator = generators.get(problemClass);
		if(generator != null) {
			return (ProblemGenerator<P, M>)generator.get(methodClass);
		} else {
			return null;
		}
	}
}
