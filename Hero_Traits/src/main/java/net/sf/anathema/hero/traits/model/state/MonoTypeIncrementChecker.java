package net.sf.anathema.hero.traits.model.state;

public class MonoTypeIncrementChecker<T> implements MappableTypeIncrementChecker<T> {
	
	private T type;
	private IncrementChecker checker;
	
	public MonoTypeIncrementChecker(IncrementChecker checker, T type) {
		this.checker = checker;
		this.type = type;
	}

	@Override
	public boolean isValidIncrement(T type, int increment) {
		if (this.type == null || type.equals(this.type)) {
			return checker.isValidIncrement(increment);
		}
		return false;
	}
}
