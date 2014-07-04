package net.sf.anathema.library.resources;

public interface Resources {

  boolean supportsKey(String key);

  String getString(String key, Object... arguments);
}