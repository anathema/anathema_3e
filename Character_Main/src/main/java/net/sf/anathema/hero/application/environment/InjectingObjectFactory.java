package net.sf.anathema.hero.application.environment;

import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.library.initialization.ObjectFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class InjectingObjectFactory implements ObjectFactory {

  private final ObjectFactory original;
  private final List<Object> injections = new ArrayList<>();

  public InjectingObjectFactory(ObjectFactory original, Object... injections) {
    this.original = original;
    this.injections.addAll(Arrays.asList(injections));
  }

  @Override
  public <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException {
    Collection<T> annotatedClasses = original.instantiateOrdered(annotation, parameter);
    return inject(annotatedClasses);
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException {
    Collection<T> annotatedClasses = original.instantiateAll(annotation, parameter);
    return inject(annotatedClasses);
  }

  @Override
  public <T> Collection<T> instantiateAllImplementers(Class<T> interfaceClass, Object... parameter) {
    Collection<T> implementers = original.instantiateAllImplementers(interfaceClass, parameter);
    return inject(implementers);
  }

  private <T> Collection<T> inject(Collection<T> implementers) {
    implementers.forEach(this::inject);
    return implementers;
  }

  private <T> void inject(T implementer) {
    Field[] fields = implementer.getClass().getFields();
    Stream.of(fields).forEach(field -> injectField(implementer, field));
  }

  private <T> void injectField(T implementer, Field field) {
    if (!field.isAnnotationPresent(Inject.class)) {
      return;
    }
    injectAssignableInjections(implementer, field);
  }

  private void injectAssignableInjections(Object implementer, Field field) {
    injections.stream().filter(injection -> field.getType().isAssignableFrom(injection.getClass())).forEach(object -> {
      try {
        field.set(implementer, object);
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    });
  }
}