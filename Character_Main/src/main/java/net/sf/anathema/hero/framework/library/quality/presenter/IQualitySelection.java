package net.sf.anathema.hero.framework.library.quality.presenter;

public interface IQualitySelection<Q extends IQuality> {

  Q getQuality();

  int getPointValue();

  boolean isCreationActive();

  boolean isExperienceActive();

  void setExperienceActive(boolean active);

  void setCreationActive(boolean active);
}