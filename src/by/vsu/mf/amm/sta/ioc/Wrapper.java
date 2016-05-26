package by.vsu.mf.amm.sta.ioc;

public class Wrapper<T> {
	private Class<T> wrappedClass;
	private String name;

	public Wrapper(Class<T> wrappedClass, String name) {
		this.wrappedClass = wrappedClass;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public T newWrappedInstance() {
		try {
			return wrappedClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int hashCode() {
		return wrappedClass == null ? 0 : wrappedClass.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if(this != object) {
			if(object != null && getClass() == object.getClass()) {
				return wrappedClass == ((Wrapper<?>)object).wrappedClass;
			}
			return false;
		}
		return true;
	}
}
