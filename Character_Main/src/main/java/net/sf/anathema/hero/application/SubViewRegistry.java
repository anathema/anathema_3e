package net.sf.anathema.hero.application;

public interface SubViewRegistry {
  <T> T get(Class<T> viewClass);
}