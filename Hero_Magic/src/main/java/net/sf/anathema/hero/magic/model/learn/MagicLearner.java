package net.sf.anathema.hero.magic.model.learn;

import net.sf.anathema.magic.data.Magic;

import java.util.Collection;

public interface MagicLearner {

  boolean handlesMagic(Magic magic);

  int getAdditionalBonusPoints(Magic magic);

  int getCreationLearnCount(Magic magic);

  Collection<? extends Magic > getLearnedMagic(boolean experienced);
}
