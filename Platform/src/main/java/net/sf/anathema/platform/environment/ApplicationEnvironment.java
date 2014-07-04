package net.sf.anathema.platform.environment;

import net.sf.anathema.library.exception.ExceptionHandler;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.preferences.Preferences;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.library.resources.ResourceFileLoader;
import net.sf.anathema.library.resources.Resources;

import java.util.Set;

public class ApplicationEnvironment implements Environment {
  private final Resources resources;
  private final ExceptionHandler handler;
  private final ResourceFileLoader loader;
  private final ObjectFactory objectFactory;
  private final Preferences preferences;

  public ApplicationEnvironment(Resources resources, ExceptionHandler handler, ResourceFileLoader loader,
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
  public Set<ResourceFile> getResourcesMatching(String namePattern) {
    return loader.getResourcesMatching(namePattern);
  }

  @Override
  public ObjectFactory getObjectFactory() {
    return objectFactory;
  }
}