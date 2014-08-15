package net.sf.anathema.library.dummy;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;

import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.library.initialization.ObjectFactory;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class ConfigurableDummyObjectFactory implements ObjectFactory {
  private final HashMultimap<Class, Object> objectsForInterfaces = HashMultimap.create();

  @Override
  public <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation,
                                              Object... parameter) throws InitializationException {
    return Collections.emptyList();
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation,
                                          Object... parameter) throws InitializationException {
    return Collections.emptyList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> Collection<T> instantiateAllImplementers(Class<T> interfaceClass, Object... parameter) {
    Set<T> objects = (Set<T>) objectsForInterfaces.get(interfaceClass);
    return new ArrayList<>(objects);
  }

  public void add(Class<?> interfaceClass, Object instance) {
    objectsForInterfaces.put(interfaceClass, instance);
  }

	@Override
	public <T> Collection<Class<? extends T>> getAllImplementers(Class<T> interfaceClass) {
		Set<? extends T> objects = (Set<? extends T>) objectsForInterfaces.get(interfaceClass);
    return (Collection<Class<? extends T>>) Lists.transform(new ArrayList(objects), object -> (Class<? extends T>)object.getClass());
	}
}
