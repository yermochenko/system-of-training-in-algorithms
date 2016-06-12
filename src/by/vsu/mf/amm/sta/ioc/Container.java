package by.vsu.mf.amm.sta.ioc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import by.vsu.mf.amm.sta.method.Method;
import by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods.seidelAlgorithm.SeidelAlgorithm;
import by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods.simpleIteration.SimpleIteration;
import by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods.TridiagonalMatrixAlgorithm;
import by.vsu.mf.amm.sta.method.example.MatrixMultiplicationMethod;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.tridiagonalMatrixAlgorithmOperations.CalculateMethodCoefficientsOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.tridiagonalMatrixAlgorithmOperations.CalculateResultVectorOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.tridiagonalMatrixAlgorithmOperations.CreateEmptyResultVectorOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.seidelAlgorithmOperations.CalculateSeidelMethodOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.seidelAlgorithmOperations.CreateEmptyResultSeidelVectorOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.simpleIterationAlgorithmOperations.CalculateSimpleIterationOperation;
import by.vsu.mf.amm.sta.method.operation.systemOfLinearEquationsProblemMethods.simpleIterationAlgorithmOperations.CreateEmptyResultSimpleIterationVectorOperation;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.method.operation.example.CalculateScalarProductOperation;
import by.vsu.mf.amm.sta.method.operation.example.CreateEmptyResultMatrixOperation;
import by.vsu.mf.amm.sta.problem.Problem;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblem;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblemSeidelMethod;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblemSimpleIterationMethod;
import by.vsu.mf.amm.sta.problem.example.MatrixMultiplicationProblem;
import by.vsu.mf.amm.sta.problem.generator.ProblemGenerator;
import by.vsu.mf.amm.sta.problem.generator.SystemOfLinearEquationsProblemGenerators.SystemOfLinearEquationsProblemGenerator;
import by.vsu.mf.amm.sta.problem.generator.SystemOfLinearEquationsProblemGenerators.SystemOfLinearEquationsSeidelProblemGenerator;
import by.vsu.mf.amm.sta.problem.generator.SystemOfLinearEquationsProblemGenerators.SystemOfLinearEquationsSimpleIterationProblemGenerator;
import by.vsu.mf.amm.sta.problem.generator.example.MatrixMultiplicationProblemGenerator;

public class Container {
	private static Map<Wrapper<? extends Problem>, Map<Wrapper<? extends Method<?>>, List<Wrapper<? extends Operation<?, ?>>>>> problems;
	private static Map<Class<? extends Problem>, Map<Class<? extends Method<?>>, ProblemGenerator<?, ?>>> generators;

	static {
		List<Wrapper<? extends Operation<?, ?>>> operations;
		Map<Wrapper<? extends Method<?>>, List<Wrapper<? extends Operation<?, ?>>>> methods;
		Map<Class<? extends Method<?>>, ProblemGenerator<?, ?>> generator;
		problems = new LinkedHashMap<>();
		generators = new LinkedHashMap<>();
		operations = new ArrayList<>();
		operations.add(new Wrapper<>(CreateEmptyResultMatrixOperation.class, "Создание матрицы результата указанного размера (или размера 0 на 0, если результат вычислен быть не может)"));
		operations.add(new Wrapper<>(CalculateScalarProductOperation.class, "Вычисление скалярного произведения двух векторов и запись значения в результирующую матрицу"));
		operations.add(new Wrapper<>(CreateEmptyResultVectorOperation.class, "Проверка условия и создание вектора ответов"));
	    operations.add(new Wrapper<>(CalculateMethodCoefficientsOperation.class, "Вычисление коэффициентов метода прогонки"));
		operations.add(new Wrapper<>(CalculateResultVectorOperation.class, "Вычисление результатов методом прогонки"));
		operations.add(new Wrapper<>(CreateEmptyResultSeidelVectorOperation.class,"Проверка условия метода Зейделя и создание вектора ответов"));
		operations.add(new Wrapper<>(CalculateSeidelMethodOperation.class,"Вычисление метода Зейделя"));
		operations.add(new Wrapper<>(CreateEmptyResultSimpleIterationVectorOperation.class,"Проверка условия метода простой итерации и создание вектора ответов"));
		operations.add(new Wrapper<>(CalculateSimpleIterationOperation.class,"Вычисление метода простой итерации"));

		methods = new LinkedHashMap<>();
		methods.put(new Wrapper<>(MatrixMultiplicationMethod.class, "Метод непосредственного умножения двух прямоугольных матриц"), operations);
		methods.put(new Wrapper<>(TridiagonalMatrixAlgorithm.class, "Метод прогонки"), operations);
		methods.put(new Wrapper<>(SeidelAlgorithm.class,"Метод Зейделя"), operations);
		methods.put(new Wrapper<>(SimpleIteration.class,"Метод простой итерации"), operations);

		problems.put(new Wrapper<>(MatrixMultiplicationProblem.class, "Задача перемножения двух матриц"), methods);		
		problems.put(new Wrapper<>(SystemOfLinearEquationsProblem.class, "Система линейных алгебраических уравнений"), methods);
		problems.put(new Wrapper<>(SystemOfLinearEquationsProblemSeidelMethod.class, "Метод Зейделя"), methods);
		problems.put(new Wrapper<>(SystemOfLinearEquationsProblemSimpleIterationMethod.class, "Метод простой итерации"), methods);
		/*
		 * bug одинаковых проблем в problems
		 */
		generator = new LinkedHashMap<>();
		generator.put(MatrixMultiplicationMethod.class, new MatrixMultiplicationProblemGenerator());
		generator.put(TridiagonalMatrixAlgorithm.class, new SystemOfLinearEquationsProblemGenerator());
		generator.put(SeidelAlgorithm.class, new SystemOfLinearEquationsSeidelProblemGenerator());
		generator.put(SimpleIteration.class, new SystemOfLinearEquationsSimpleIterationProblemGenerator());
		
		generators.put(MatrixMultiplicationProblem.class, generator);
		generators.put(SystemOfLinearEquationsProblem.class, generator);
		generators.put(SystemOfLinearEquationsProblemSeidelMethod.class, generator);
		generators.put(SystemOfLinearEquationsProblemSimpleIterationMethod.class, generator);
		/*
         * bug одинаковых проблем в generators
         */
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
