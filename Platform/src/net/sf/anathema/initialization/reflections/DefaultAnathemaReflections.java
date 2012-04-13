package net.sf.anathema.initialization.reflections;

import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import com.google.common.base.Function;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static com.google.common.collect.Collections2.transform;

public class DefaultAnathemaReflections implements AnathemaReflections {

  private ClassLoader[] classLoaders;
  private Reflections reflections;
  private URL repositoryURL;

  public DefaultAnathemaReflections(IAnathemaPreferences preferences) {
	initRepositoryURL(new RepositoryLocationResolver(preferences).resolve());
    ConfigurationBuilder configuration = createConfiguration();
    this.reflections = new Reflections(configuration);
  }
  
  private void initRepositoryURL(String path) {
	  try {
	    	File testFile = new File(path);
			repositoryURL = testFile.toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
  }

  @Override
  public Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
    return reflections.getTypesAnnotatedWith(annotation, false);
  }

  @Override
  public Set<IAnathemaResource> getResourcesMatching(String namepattern) {
    Pattern pattern = Pattern.compile(namepattern);
    return new HashSet<IAnathemaResource>(transform(reflections.getResources(pattern), new ToResource()));
  }

  private ConfigurationBuilder createConfiguration() {
    String[] prefixes = new String[]{"net.sf.anathema", "data", "custom"};
    return createIdeCompatibleConfiguration(prefixes);
  }

  private ConfigurationBuilder createIdeCompatibleConfiguration(String[] prefixes) {
    classLoaders = getClassLoaders();
    return new IdeCompatibleConfiguration(prefixes, classLoaders);
  }

  private ClassLoader[] getClassLoaders() {
	List<ClassLoader> classLoaders = new ArrayList<ClassLoader>();
    classLoaders.add(Thread.currentThread().getContextClassLoader());
    classLoaders.add(Reflections.class.getClassLoader());
    if (repositoryURL != null) {
    	classLoaders.add(new URLClassLoader(new URL[] { repositoryURL }));
    }
    return classLoaders.toArray(new ClassLoader[0]);
  }
  
  public ClassLoader getClassLoaderForResource(String resource) {
	  for (ClassLoader loader : classLoaders) {
		  if (loader.getResource(resource) != null)
			  return loader;
	  }
	  return null;
  }
  
  private class ToResource implements Function<String, IAnathemaResource> {
	    @Override
	    public IAnathemaResource apply(String resource) {
	    	ClassLoader loaderForResource = null;
	    	for (ClassLoader loader : classLoaders) {
	  		  if (loader.getResource(resource) != null) {
	  			 loaderForResource = loader;
	  		  }
	  	  	}
	    	return new AnathemaResource(resource, loaderForResource);
	    }
  	}
}