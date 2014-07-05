package net.sf.anathema.hero.traits.model.state;

public interface MappableTypeIncrementChecker<T> {

	int NO_LIMIT = Integer.MAX_VALUE;
	
	boolean isValidIncrement(T type, int increment);
}
