package net.sf.anathema.hero.application.environment;

import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.library.initialization.ObjectFactory;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class InjectingObjectFactory implements ObjectFactory {

  private ObjectFactory objectFactory;
  private Map<Class, Object> injectObjects;

  public InjectingObjectFactory(ObjectFactory objectFactory, Map<Class, Object> injectObjects) {
    this.objectFactory = objectFactory;
    this.injectObjects = injectObjects;
  }

  @Override
  public <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation,
                                              Object... parameter) throws InitializationException {
    Collection<T> createdObjects = objectFactory.instantiateOrdered(annotation, parameter);
    return injectAll(createdObjects);
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation,
                                          Object... parameter) throws InitializationException {
    Collection<T> createdObject = objectFactory.instantiateAll(annotation, parameter);
    return injectAll(createdObject);
  }

  @Override
  public <T> Collection<T> instantiateAllImplementers(Class<T> interfaceClass, Object... parameter) {
    Collection<T> createdObject = objectFactory.instantiateAllImplementers(interfaceClass, parameter);
    return injectAll(createdObject);
  }

  private <T> Collection<T> injectAll(Collection<T> instances) {
    instances.forEach(this::inject);
    return instances;
  }

  private <T> void inject(T instance) {
    List<Field> fields =  FieldUtils.getAllFieldsList(instance.getClass());
    fields.forEach(field -> {
      try {
        Class<?> type = field.getType();
        if (field.isAnnotationPresent(Inject.class) && injectObjects.containsKey(type)) {
          Object value = injectObjects.get(field.getType());
          FieldUtils.writeField(field, instance, value);
        }
      } catch (IllegalAccessException e) {
        throw new InitializationException("Could not inject.", e);
      }
    });
  }
}
