package net.sf.anathema.hero.framework.library.quality.presenter;

import net.sf.anathema.library.identifier.Identifier;

public interface IQuality extends Identifier {

  IQualityType getType();

  boolean prerequisitesFulfilled(IQualitySelection<? extends IQuality>[] selectedQualities);
}