package net.sf.anathema.hero.framework.display;

public interface SubViewRegistry {
  <T> T get(Class<T> viewClass);
}