package net.sf.anathema.hero.framework.library.quality.presenter;

import net.sf.anathema.lib.util.Identifier;

public interface IQuality extends Identifier {

  IQualityType getType();

  boolean prerequisitesFulfilled(IQualitySelection<? extends IQuality>[] selectedQualities);
}