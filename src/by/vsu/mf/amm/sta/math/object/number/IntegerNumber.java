package by.vsu.mf.amm.sta.math.object.number;

public interface IntegerNumber extends RationalNumber, Iterable<Byte> {
	IntegerNumber ZERO = new SimpleIntegerNumberImpl(0);

	IntegerNumber ONE = new SimpleIntegerNumberImpl(1);
}