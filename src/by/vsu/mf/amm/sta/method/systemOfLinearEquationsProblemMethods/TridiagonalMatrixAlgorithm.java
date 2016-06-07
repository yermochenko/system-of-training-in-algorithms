package by.vsu.mf.amm.sta.method.systemOfLinearEquationsProblemMethods;

import by.vsu.mf.amm.sta.math.object.Vector;
import by.vsu.mf.amm.sta.method.Method;
import by.vsu.mf.amm.sta.problem.SystemOfLinearEquationsProblem;

public class TridiagonalMatrixAlgorithm extends Method<SystemOfLinearEquationsProblem> {

    private Vector methodCoefficientsA;
    private Vector methodCoefficientsB;

    private int currentCoefficientA;
    private int currentCoefficientB;

    private int currentUnknownX;

    private boolean coefficientsFound = false;
    private boolean diagonallyDominantMatrix = false;


    public Vector getMethodCoefficientsA() {
        return methodCoefficientsA;
    }

    public void setMethodCoefficientsA(Vector methodCoefficientsA) {
        this.methodCoefficientsA = methodCoefficientsA;
    }

    public Vector getMethodCoefficientsB() {
        return methodCoefficientsB;
    }

    public void setMethodCoefficientsB(Vector methodCoefficientsB) {
        this.methodCoefficientsB = methodCoefficientsB;
    }

    public int getCurrentCoefficientA() {
        return currentCoefficientA;
    }

    public void setCurrentCoefficientA(int currentCoefficientA) {
        this.currentCoefficientA = currentCoefficientA;
    }

    public int getCurrentCoefficientB() {
        return currentCoefficientB;
    }

    public void setCurrentCoefficientB(int currentCoefficientB) {
        this.currentCoefficientB = currentCoefficientB;
    }

    public int getCurrentUnknownX() {
        return currentUnknownX;
    }

    public void setCurrentUnknownX(Integer currentUnknownX) {
        this.currentUnknownX = currentUnknownX;
    }

    public boolean isCoefficientsFound() {
        return coefficientsFound;
    }

    public void setCoefficientsFound(boolean coefficientsFound) {
        this.coefficientsFound = coefficientsFound;
    }

    public boolean isDiagonallyDominantMatrix() {
        return diagonallyDominantMatrix;
    }

    public void setDiagonallyDominantMatrix(boolean diagonallyDominantMatrix) {
        this.diagonallyDominantMatrix = diagonallyDominantMatrix;
    }
}
