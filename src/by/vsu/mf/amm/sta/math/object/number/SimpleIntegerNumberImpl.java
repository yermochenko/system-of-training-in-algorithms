package by.vsu.mf.amm.sta.math.object.number;

import java.util.Iterator;

public class SimpleIntegerNumberImpl implements IntegerNumber {
	private long number;

	public SimpleIntegerNumberImpl(long number) {
		this.number = number;
	}

	public long value() {
		return number;
	}

	@Override
	public IntegerNumber numerator() {
		return this;
	}

	@Override
	public IntegerNumber denominator() {
		return IntegerNumber.ONE;
	}

	@Override
	public boolean positive() {
		return number >= 0;
	}

	@Override
	public Iterator<Byte> iterator() {
		return new DigitsIterator(Math.abs(number));
	}

	@Override
	public boolean equals(Object object) {
		if(this != object) {
			if(object instanceof IntegerNumber) {
				return true; // TODO: compare numbers by digits
			}
			return false;
		}
		return true;
	}

	private class DigitsIterator implements Iterator<Byte> {
		private long number;
		private boolean zero;

		public DigitsIterator(long number) {
			this.number = number;
			zero = number == 0;
		}

		@Override
		public boolean hasNext() {
			return zero || number != 0;
		}

		@Override
		public Byte next() {
			if(zero) {
				zero = false;
				return 0;
			}
			byte digit = (byte)(number % 10);
			number /= 10;
			return digit;
		}
	}
}