package net.sf.anathema.platform.environment;

import net.sf.anathema.library.exception.ExceptionHandler;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.preferences.Preferences;
import net.sf.anathema.library.resources.ResourceFileLoader;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.dependencies.InterfaceFinder;

public interface Environment extends Resources, ExceptionHandler, Preferences, ResourceFileLoader {

  ObjectFactory getObjectFactory();

  InterfaceFinder getInterfaceFinder();
}