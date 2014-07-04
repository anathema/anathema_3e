package net.sf.anathema.hero.individual.view;

public interface SectionView {

  <T> T addView(String title, Class<T> viewClass);

  void finishInitialization();
}