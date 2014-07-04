package net.sf.anathema.platform.environment;

import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.preferences.Preferences;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.library.resources.ResourceLoader;
import net.sf.anathema.library.resources.Resources;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Set;

public class ApplicationEnvironment implements Environment {
  private final Resources resources;
  private final ExceptionHandler handler;
  private final ResourceLoader loader;
  private final ObjectFactory objectFactory;
  private final Preferences preferences;

  public ApplicationEnvironment(Resources resources, ExceptionHandler handler, ResourceLoader loader,
                                ObjectFactory objectFactory, Preferences preferences) {
    this.resources = resources;
    this.handler = handler;
    this.loader = loader;
    this.objectFactory = objectFactory;
    this.preferences = preferences;
  }

  @Override
  public void handle(Throwable exception) {
    handler.handle(exception);
  }

  @Override
  public void handle(Throwable throwable, String message) {
    handler.handle(throwable, message);
  }

  @Override
  public boolean supportsKey(String key) {
    return resources.supportsKey(key);
  }

  @Override
  public String getString(String key, Object... arguments) {
    return resources.getString(key, arguments);
  }

  @Override
  public String getPreference(String key) {
    return preferences.getPreference(key);
  }

  @Override
  public <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation,
                                              Object... parameter) throws InitializationException {
    return objectFactory.instantiateOrdered(annotation, parameter);
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation,
                                          Object... parameter) throws InitializationException {
    return objectFactory.instantiateAll(annotation, parameter);
  }

  @Override
  public <T> Collection<T> instantiateAllImplementers(Class<T> interfaceClass, Object... parameter) {
    return objectFactory.instantiateAllImplementers(interfaceClass, parameter);
  }

  @Override
  public Set<ResourceFile> getResourcesMatching(String namePattern) {
    return loader.getResourcesMatching(namePattern);
  }
}