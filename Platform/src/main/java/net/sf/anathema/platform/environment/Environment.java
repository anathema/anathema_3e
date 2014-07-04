package net.sf.anathema.platform.environment;

import net.sf.anathema.library.exception.ExceptionHandler;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.preferences.Preferences;
import net.sf.anathema.library.resources.ResourceFileLoader;
import net.sf.anathema.library.resources.Resources;

public interface Environment extends Resources, ExceptionHandler, Preferences, ResourceFileLoader {

  ObjectFactory getObjectFactory();
  //nothing to do
}