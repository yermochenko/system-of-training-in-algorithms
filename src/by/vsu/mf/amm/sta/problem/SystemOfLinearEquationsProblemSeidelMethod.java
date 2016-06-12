package by.vsu.mf.amm.sta.problem;

import by.vsu.mf.amm.sta.math.object.Matrix;
import by.vsu.mf.amm.sta.math.object.Vector;

public class SystemOfLinearEquationsProblemSeidelMethod extends Problem {

    private Matrix matrix;
    private Vector freeMembers;
    private Vector result;

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public Vector getFreeMembers() {
        return freeMembers;
    }

    public void setFreeMembers(Vector freeMembers) {
        this.freeMembers = freeMembers;
    }

    public Vector getResult() {
        return result;
    }

    public void setResult(Vector result) {
        this.result = result;
    }

}
