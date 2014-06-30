package net.sf.anathema.hero.framework.library.quality.presenter;

public interface IQualityPredicate {

  boolean isFulfilled(IQualitySelection<? extends IQuality>[] selectedQualities);
}