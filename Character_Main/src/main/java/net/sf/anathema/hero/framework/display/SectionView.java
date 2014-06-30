package net.sf.anathema.hero.framework.display;

public interface SectionView {

  <T> T addView(String title, Class<T> viewClass);

  void finishInitialization();
}