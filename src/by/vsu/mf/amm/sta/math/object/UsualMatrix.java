package by.vsu.mf.amm.sta.math.object;

import by.vsu.mf.amm.sta.Entity;

public class UsualMatrix extends Entity implements MutableMatrix {
	private double[][] data;

	public UsualMatrix(int rowsQuantity, int columnsQuantity) {
		if(rowsQuantity > 0 && columnsQuantity > 0) {
			data = new double[rowsQuantity][columnsQuantity];
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int rowsQuantity() {
		return data.length;
	}

	@Override
	public int columnsQuantity() {
		return data[0].length;
	}

	@Override
	public double get(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	public void set(int rowIndex, int columnIndex, double value) {
		data[rowIndex][columnIndex] = value;
	}
}
