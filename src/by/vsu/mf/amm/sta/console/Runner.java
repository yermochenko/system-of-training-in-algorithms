package by.vsu.mf.amm.sta.console;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import by.vsu.mf.amm.sta.console.visualizer.VisualizerContainer;
import by.vsu.mf.amm.sta.exception.operation.IncorrectOperandOperationException;
import by.vsu.mf.amm.sta.exception.operation.IncorrectOperationException;
import by.vsu.mf.amm.sta.exception.operation.MatrixSizeException;
import by.vsu.mf.amm.sta.exception.operation.OperationException;
import by.vsu.mf.amm.sta.exception.visualization.VisualizationException;
import by.vsu.mf.amm.sta.ioc.Container;
import by.vsu.mf.amm.sta.ioc.Wrapper;
import by.vsu.mf.amm.sta.method.Method;
import by.vsu.mf.amm.sta.method.operation.Operation;
import by.vsu.mf.amm.sta.problem.Problem;

public class Runner {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println("Программа тренировки алгоритмов решения различных задач.");
		System.out.println();
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			System.out.println("Выберите одну из имеющихся задач:");
			List<Wrapper<? extends Problem>> problems = Container.getProblems();
			for(int index = 0; index < problems.size(); index++) {
				System.out.printf("\t%d.\t%s\n", index + 1, problems.get(index).getName());
			}
			System.out.println();
			System.out.print("Введите номер задачи > ");
			Problem problem = problems.get(scanner.nextInt() - 1).newWrappedInstance();
			List<Wrapper<? extends Method<?>>> methods = Container.getMethods(problem.getClass());
			if(methods != null) {
				Method<Problem> method = null;
				if(methods.size() > 1) {
					System.out.println("Выберите метод решения задачи:");
					for(int index = 0; index < methods.size(); index++) {
						System.out.printf("\t%d.\t%s\n", index + 1, methods.get(index).getName());
					}
					System.out.println();
					System.out.print("Введите номер метода > ");
					try {
						method = (Method<Problem>)methods.get(scanner.nextInt() - 1).newWrappedInstance();
					} catch(InputMismatchException e) {
						System.out.println("Номер метода должен быть натуральным числом");
					} catch(IndexOutOfBoundsException e) {
						System.out.println("Метода с таким номером нет в списке");
					}
				} else if(methods.size() == 1) {
					System.out.println("Для данной задачи доступен один метод: " + methods.get(0).getName());
					System.out.println("Желаете продолжить решение задачи предлагаемым методом?");
					System.out.println("\t1.\tДа");
					System.out.println("\t2.\tНет");
					System.out.println();
					System.out.print("Введите номер ответа > ");
					try {
						switch(scanner.nextInt()) {
							case 1:
								method = (Method<Problem>)methods.get(0).newWrappedInstance();
								break;
							case 2:
								System.out.println("Для выбора другой задачи перезапустите программу");
								break;
							default:
								System.out.println("Ответа с таким номером нет в списке");
						}
					} catch(InputMismatchException e) {
						System.out.println("Номер ответа должен быть натуральным числом");
					}
				} else {
					System.out.println("Список методов для данной задачи пуст");
				}
				if(method != null) {
					Container.getGenerator(problem.getClass(), method.getClass()).generate(problem, method);
					method.setProblem(problem);
					List<Wrapper<? extends Operation<?, ?>>> operations = Container.getOperations(problem.getClass(), method.getClass());
					try {
						while(!method.isSolutionFinished()) {
							VisualizerContainer.getProblemVisualizer(problem.getClass(), method.getClass()).output(problem, method);
							System.out.println("Выберите одну из возможных операций:");
							for(int index = 0; index < operations.size(); index++) {
								System.out.printf("\t%d.\t%s\n", index + 1, operations.get(index).getName());
							}
							System.out.println();
							System.out.print("Введите номер операции > ");
							Operation<?, ?> operation = operations.get(scanner.nextInt() - 1).newWrappedInstance();
							VisualizerContainer.getOperationVisualizer(problem.getClass(), operation.getClass(), scanner).input(operation);
							method.execute((Operation<Problem, Method<Problem>>) operation);
						}
						VisualizerContainer.getProblemVisualizer(problem.getClass(), method.getClass()).output(problem, method);
						if(problem.isSolved()) {
							System.out.println("Поздравляем! Задача успешно решена");
						} else {
							System.out.println("Поздравляем! Показано, что задача не имеет решения");
						}
					} catch(InputMismatchException e) {
						System.out.println("Номер операции должен быть натуральным числом");
					} catch(IndexOutOfBoundsException e) {
						System.out.println("Операции с таким номером нет в списке");
					} catch(VisualizationException e) {
					} catch(IncorrectOperationException e) {
						System.out.println("Неверно выбрана операция");
					} catch(MatrixSizeException e) {
						System.out.println("Неверно указан размер матрицы-результата");
					} catch(IncorrectOperandOperationException e) {
						System.out.println("Неверно заданы параметры операции");
					} catch(OperationException e) {
						System.out.println("Неизвестная ошибка выполнения операции");
					}
				}
			} else {
				System.out.println("Для данной задачи не задан список методов");
			}
		} catch(InputMismatchException e) {
			System.out.println("Номер задачи должен быть натуральным числом");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Задачи с таким номером нет в списке");
		} finally {
			scanner.close();
		}
	}
}
