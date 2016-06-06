package by.vsu.mf.amm.sta.math.object;

import by.vsu.mf.amm.sta.Entity;

public class TridiagonalMatrix extends Entity implements MutableMatrix {

    private double[] mainDiagonal;
    private double[] diagonalBelowMain;
    private double[] diagonalAboveMain;

    public TridiagonalMatrix(int Quantity) {
        if (Quantity > 0) {
            mainDiagonal = new double[Quantity];
            diagonalBelowMain = new double[Quantity - 1];
            diagonalAboveMain = new double[Quantity - 1];
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int rowsQuantity() {
        return mainDiagonal.length;
    }

    @Override
    public int columnsQuantity() {
        return mainDiagonal.length;
    }

    @Override
    public double get(int rowIndex, int columnIndex) {
            switch (rowIndex - columnIndex) {
                case 0:
                    return mainDiagonal[rowIndex];
                case -1:
                    return diagonalAboveMain[rowIndex];
                case 1:
                    return diagonalBelowMain[columnIndex];
                default:
                    return 0;
            }
    }

    @Override
    public void set(int rowIndex, int columnIndex, double value) {
        switch (rowIndex - columnIndex) {
            case 0:
                mainDiagonal[rowIndex] = value;
                break;
            case -1:
                diagonalAboveMain[rowIndex] = value;
                break;
            case 1:
                diagonalBelowMain[columnIndex] = value;
                break;
            default:
                throw new IllegalArgumentException();
        }

    }

}
