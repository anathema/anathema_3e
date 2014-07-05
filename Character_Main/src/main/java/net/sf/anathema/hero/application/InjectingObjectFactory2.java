package net.sf.anathema.hero.application;

import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.library.initialization.ObjectFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InjectingObjectFactory2 implements ObjectFactory {
  private ObjectFactory original;
  private final Map<Class, Object> injections = new HashMap<>();

  public InjectingObjectFactory2(ObjectFactory original, Object... injections) {
    this.original = original;
    for (Object injection : injections) {
      this.injections.put(injection.getClass(), injection);
    }
  }

  @Override
  public <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException {
    Collection<T> annotatedClasses = original.instantiateOrdered(annotation, parameter);
    inject(annotatedClasses);
    return annotatedClasses;
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException {
    Collection<T> annotatedClasses = original.instantiateAll(annotation, parameter);
    inject(annotatedClasses);
    return annotatedClasses;
  }

  @Override
  public <T> Collection<T> instantiateAllImplementers(Class<T> interfaceClass, Object... parameter) {
    Collection<T> implementers = original.instantiateAllImplementers(interfaceClass, parameter);
    inject(implementers);
    return implementers;
  }

  private <T> void inject(Collection<T> implementers) {
    implementers.forEach(this::inject);
  }

  private <T> void inject(T implementer) {
    Field[] fields = implementer.getClass().getFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(Inject2.class) && injections.containsKey(field.getType())) {
        setFieldValue(implementer, field);
      }
    }
  }

  private <T> void setFieldValue(T implementer, Field field) {
    try {
      Object newValue = injections.get(field.getType());
      field.set(implementer, newValue);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}