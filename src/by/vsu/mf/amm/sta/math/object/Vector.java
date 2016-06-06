package by.vsu.mf.amm.sta.math.object;

import by.vsu.mf.amm.sta.Entity;

public class Vector extends Entity {

    double[] data;

    public Vector(int Quantity) {
        if (Quantity > 0) {
            data = new double[Quantity];
        } else {
            throw new IllegalArgumentException();
        }

    }


    public int getQuantity() {

        return data.length;
    }

    public double get(int index) {
        return data[index];

    }

    public void set(int index, double value) {

        data[index] = value;
    }
}
