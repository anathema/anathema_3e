package net.sf.anathema.platform.dependencies;

import java.util.Set;

public interface InterfaceFinder {
  public <T> Set<Class<? extends T>> findAll(Class<T> interfaceClass);
}
