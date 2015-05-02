package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.charms.display.presenter.CharmTreeArbitrator;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CategoryReference;

import java.util.Collection;
import java.util.List;

public interface CharmOptions extends CharmTreeArbitrator {

  CharmMap getCharmIdMap();

  Collection<CharmSpecialLearning> getSpecialLearningCharms();

  Collection<Charm> filterAvailableCharms(CharmTree tree);
  
  boolean isAlienCharmsAllowedForHero();

  List<CategoryReference> getValidCategoryReferencesForHero();
}
