package net.sf.anathema.platform.persistence;

import net.sf.anathema.platform.dependencies.InterfaceFinder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PolymorphicTypeAdapterFactoryFactory {

  private InterfaceFinder finder;

  public PolymorphicTypeAdapterFactoryFactory(InterfaceFinder finder) {
    this.finder = finder;
  }

  public RuntimeTypeAdapterFactory[] generateFactories(Class<?>... baseClasses) {
    Stream<Class<?>> classes = Stream.of(baseClasses);
    //If you see a "cyclic inference" below, don't worry about it - it's a bug in IntelliJ IDEA, http://youtrack.jetbrains.com/issue/IDEA-124983 and http://youtrack.jetbrains.com/issue/IDEA-124151
    List<RuntimeTypeAdapterFactory> list = classes.map(this::generateFactory).filter(Objects::nonNull).collect(toList());
    return list.toArray(new RuntimeTypeAdapterFactory[list.size()]);
  }

  private <T> RuntimeTypeAdapterFactory<T> generateFactory(Class<T> baseClass) {
    String field = baseClass.getAnnotation(JsonField.class).value();
    RuntimeTypeAdapterFactory<T> factory = RuntimeTypeAdapterFactory.of(baseClass, field);
    Collection<Class<? extends T>> implementations = finder.findAll(baseClass);
    implementations.forEach(implementingClass -> {
      String type = implementingClass.getAnnotation(JsonType.class).value();
      factory.registerSubtype(implementingClass, type);
    });
    return factory;
  }
}