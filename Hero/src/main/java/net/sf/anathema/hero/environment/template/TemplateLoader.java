package net.sf.anathema.hero.environment.template;

import net.sf.anathema.library.resources.ResourceFile;

import java.io.InputStream;

public interface TemplateLoader<T> {

  T load(ResourceFile file);

  T load(InputStream inputStream);
}