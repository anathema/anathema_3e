package net.sf.anathema.platform.dependencies;

import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.library.resources.ResourceFileLoader;
import net.sf.anathema.platform.resources.InternalResourceFile;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class DefaultAnathemaReflections implements ResourceFileLoader, AnnotationFinder, InterfaceFinder {

  private ClassLoader[] classLoaders;
  private Reflections reflections;

  public DefaultAnathemaReflections() {
    this.classLoaders = getClassLoaders();
    ConfigurationBuilder configuration = createConfiguration();
    this.reflections = new Reflections(configuration);
  }

  @Override
  public Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
    return reflections.getTypesAnnotatedWith(annotation, false);
  }

  @Override
  public Set<ResourceFile> getResourcesMatching(String namePattern) {
    Pattern pattern = Pattern.compile(namePattern);
    Stream<String> resourceNames = reflections.getResources(pattern).stream();
    return resourceNames.map(this::toResource).collect(toSet());
  }

  @Override
  public <T> Set<Class<? extends T>> findAll(Class<T> interfaceClass) {
    return reflections.getSubTypesOf(interfaceClass);
  }

  private ConfigurationBuilder createConfiguration() {
    String[] prefixes = new String[]{"net.sf.anathema", "data"};
    return new IdeCompatibleConfiguration(prefixes, classLoaders);
  }

  private ClassLoader[] getClassLoaders() {
    ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    ClassLoader staticClassLoader = Reflections.class.getClassLoader();
    return new ClassLoader[]{contextClassLoader, staticClassLoader};
  }

  private ResourceFile toResource(String resource) {
    ClassLoader loaderForResource = null;
    for (ClassLoader loader : classLoaders) {
      if (loader.getResource(resource) != null) {
        loaderForResource = loader;
      }
    }
    return new InternalResourceFile(resource, loaderForResource);
  }
}