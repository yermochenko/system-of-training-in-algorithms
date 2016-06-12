package by.vsu.mf.amm.sta.console.visualizer.systemOfLinearEquationsProblemVisualizerSimpleIteration;

import by.vsu.mf.amm.sta.console.visualizer.ProblemVisualizer;
import by.vsu.mf.amm.sta.math.object.Matrix;
import by.vsu.mf.amm.sta.math.object.Vector;
import by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods.simpleIteration.SimpleIteration;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblemSimpleIterationMethod;

public class SystemOfLinearEquationsProblemVisualizerSimpleIteration
        implements ProblemVisualizer<SystemOfLinearEquationsProblemSimpleIterationMethod, SimpleIteration> {

    @Override
    public void output(SystemOfLinearEquationsProblemSimpleIterationMethod problem, SimpleIteration method) {

        Matrix matrix = problem.getMatrix();
        Vector vector = problem.getFreeMembers();
        Vector result = problem.getResult();

        System.out.println("Система линейных алгебраических уравнений");
        output(matrix, vector);
        if (result != null) {
            System.out.println("Текущий вектор-результата ");
            output(result);
        }
    }

    private void output(Matrix matrix, Vector vector) {

        for (int i = 0; i < matrix.rowsQuantity(); i++) {
            System.out.print("|");
            for (int j = 0; j < matrix.columnsQuantity(); j++) {
                System.out.printf("%7.1f", matrix.get(i, j) /* + "\t" */);
            }
            System.out.print("|\t");
            System.out.println("\t| x" + (i + 1) + " |\t|" + vector.get(i) + " |");
        }
    }

    private void output(Vector vector) {

        for (int i = 0; i < vector.getQuantity(); i++) {
            System.out.print(vector.get(i) + "\t");
        }
        System.out.println();
    }
}
