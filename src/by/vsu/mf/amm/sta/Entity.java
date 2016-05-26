package by.vsu.mf.amm.sta;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import by.vsu.mf.amm.sta.exception.CloneRuntimeException;

abstract public class Entity implements Serializable, Cloneable {
	@Override
	public Object clone() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch(IOException | ClassNotFoundException e) {
			throw new CloneRuntimeException(e);
		} finally {
			try { oos.close(); } catch(NullPointerException | IOException e ) {}
			try { ois.close(); } catch(NullPointerException | IOException e ) {}
		}
	}
}
