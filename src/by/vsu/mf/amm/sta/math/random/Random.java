package by.vsu.mf.amm.sta.math.random;

public class Random extends java.util.Random {
	public int nextInt(int min, int max) {
		return nextInt(max - min + 1) + min;
	}
}
