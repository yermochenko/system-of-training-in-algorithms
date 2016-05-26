package by.vsu.mf.amm.sta.math.object;

import java.io.Serializable;

public interface Matrix extends Serializable {
	int rowsQuantity();
	int columnsQuantity();
	double get(int rowIndex, int columnIndex);
}
