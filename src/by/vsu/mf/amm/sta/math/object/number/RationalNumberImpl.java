package by.vsu.mf.amm.sta.math.object.number;

import by.vsu.mf.amm.sta.exception.math.ZeroDivideException;

public class RationalNumberImpl implements RationalNumber {
	private IntegerNumber numerator;
	private IntegerNumber denominator;

	public RationalNumberImpl(IntegerNumber numerator, IntegerNumber denominator) {
		if(!IntegerNumber.ZERO.equals(denominator)) {
			this.numerator = numerator;
			this.denominator = denominator;
			// TODO: abbraviate fraction and choose sign from numerator & denominator
		} else {
			throw new ZeroDivideException();
		}
	}

	@Override
	public IntegerNumber numerator() {
		return numerator;
	}

	@Override
	public IntegerNumber denominator() {
		return denominator;
	}

	@Override
	public boolean positive() {
		return numerator.positive();
	}
}