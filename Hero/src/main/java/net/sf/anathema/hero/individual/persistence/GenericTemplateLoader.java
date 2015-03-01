package net.sf.anathema.hero.individual.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.io.InputOutput;
import net.sf.anathema.library.resources.ResourceFile;

import java.io.IOException;
import java.io.InputStream;

public class GenericTemplateLoader<T> implements TemplateLoader<T> {

  private final Gson gson;
  private final Class<T> aClass;

  public GenericTemplateLoader(Class<T> aClass) {
    this(aClass, new GenericAdapter[0]);
  }

  public GenericTemplateLoader(Class<T> aClass, GenericAdapter... adapters) {
    this.aClass = aClass;
    GsonBuilder gsonBuilder = new GsonBuilder();
    for (GenericAdapter adapter : adapters) {
      gsonBuilder.registerTypeAdapter(adapter.type, adapter.adapter);
    }
    gson = gsonBuilder.create();
  }
  
  public GenericTemplateLoader(Class<T> aClass, TypeAdapterFactory... adapterFactories) {
	    this.aClass = aClass;
	    GsonBuilder gsonBuilder = new GsonBuilder();
	    for (TypeAdapterFactory factory : adapterFactories) {
	      gsonBuilder.registerTypeAdapterFactory(factory);
	    }
	    gson = gsonBuilder.create();
	  }
  
  @Override
  public T load(ResourceFile resource) {
    try (InputStream inputStream = resource.getURL().openStream()) {
      return load(inputStream);
    } catch (Exception e) {
      throw new PersistenceException("Error loading " + resource.getFileName(), e);
    }
  }

  @Override
  public T load(InputStream inputStream) {
    try {
      String json = InputOutput.toString(inputStream);
      return gson.fromJson(json, aClass);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }
}