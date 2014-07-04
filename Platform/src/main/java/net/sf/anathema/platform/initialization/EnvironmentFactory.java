package net.sf.anathema.platform.initialization;

import net.sf.anathema.library.exception.ExceptionHandler;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.preferences.Preferences;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.library.resources.ResourceFileLoader;
import net.sf.anathema.platform.dependencies.AggregatedResourceFileLoader;
import net.sf.anathema.platform.dependencies.DefaultAnathemaReflections;
import net.sf.anathema.platform.dependencies.ReflectionObjectFactory;
import net.sf.anathema.platform.environment.ApplicationEnvironment;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.preferences.PropertyPreferences;
import net.sf.anathema.platform.repository.CustomDataResourceFileLoader;
import net.sf.anathema.platform.repository.PreferencesBasedRepositoryLocation;
import net.sf.anathema.platform.repository.RepositoryLocationResolver;
import net.sf.anathema.platform.resources.LocaleResources;

import java.util.Set;

public class EnvironmentFactory {

  private final ExceptionHandler exceptionHandler;

  public EnvironmentFactory(ExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

  public Environment create() {
    Preferences preferences = new PropertyPreferences();
    DefaultAnathemaReflections reflections = new DefaultAnathemaReflections();
    ResourceFileLoader loader = createResourceLoaderForInternalAndCustomResources(exceptionHandler, reflections, preferences);
    ObjectFactory objectFactory = new ReflectionObjectFactory(reflections, reflections);
    LocaleResources resources = initResources(loader);
    return new ApplicationEnvironment(resources, exceptionHandler, loader, objectFactory, preferences);
  }

  private LocaleResources initResources(ResourceFileLoader loader) {
    LocaleResources resources = new LocaleResources();
    Set<ResourceFile> resourcesInPaths = loader.getResourcesMatching(".*\\.properties");
    for (ResourceFile resource : resourcesInPaths) {
      resources.addResourceBundle(resource);
    }
    return resources;
  }

  private ResourceFileLoader createResourceLoaderForInternalAndCustomResources(ExceptionHandler exceptionHandler, DefaultAnathemaReflections reflections, Preferences preferences) {
    try {
      PreferencesBasedRepositoryLocation location = new PreferencesBasedRepositoryLocation(preferences);
      RepositoryLocationResolver resolver = new RepositoryLocationResolver(location);
      CustomDataResourceFileLoader customLoader = new CustomDataResourceFileLoader(resolver);
      return new AggregatedResourceFileLoader(reflections, customLoader);
    } catch (RuntimeException e) {
      exceptionHandler.handle(e);
      return new AggregatedResourceFileLoader(reflections);
    }
  }
}
