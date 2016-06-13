package by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods.simpleIteration;

import by.vsu.mf.amm.sta.math.object.Vector;
import by.vsu.mf.amm.sta.math.object.UsualMatrix;
import by.vsu.mf.amm.sta.method.Method;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblemSimpleIterationMethod;;

public class SimpleIteration extends Method<SystemOfLinearEquationsProblemSimpleIterationMethod> {
    private int accuracy;
    private int quantity;

    private UsualMatrix matrixB;

    private Vector approximateVector;
    private Vector vectorG;

    private boolean diagonallyDominantMatrix = false;

    public void createMatrixB() {
        matrixB = new UsualMatrix(quantity, quantity);
    }

    public void createVectorAppoximate() {
        approximateVector = new Vector(quantity);
    }

    public void createVectorG() {
        vectorG = new Vector(quantity);
    }

    public UsualMatrix getMatrixB() {
        return matrixB;
    }

    public void setMatrixB(UsualMatrix matrixB) {
        this.matrixB = matrixB;
    }

    public Vector getApproximateVector() {
        return approximateVector;
    }

    public void setApproximateVector(Vector approximateVector) {
        this.approximateVector = approximateVector;
    }

    public Vector getVectorG() {
        return vectorG;
    }

    public void setVectorG(Vector vectorG) {
        this.vectorG = vectorG;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isDiagonallyDominantMatrix() {
        return diagonallyDominantMatrix;
    }

    public void setDiagonallyDominantMatrix(boolean diagonallyDominantMatrix) {
        this.diagonallyDominantMatrix = diagonallyDominantMatrix;
    }
}
