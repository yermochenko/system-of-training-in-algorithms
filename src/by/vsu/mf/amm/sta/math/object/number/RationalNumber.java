package by.vsu.mf.amm.sta.math.object.number;

import by.vsu.mf.amm.sta.math.object.MathObject;

public interface RationalNumber extends MathObject {
	IntegerNumber numerator();

	IntegerNumber denominator();

	boolean positive();
}