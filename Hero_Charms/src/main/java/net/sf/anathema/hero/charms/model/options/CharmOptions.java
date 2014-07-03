package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.display.presenter.CharmTreeArbitrator;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.charm.data.Charm;

import java.util.List;

public interface CharmOptions extends CharmTreeArbitrator {

  CharmMap getCharmIdMap();

  ISpecialCharm[] getSpecialCharms();

  Charm[] filterAvailableCharms(CharmTree tree);

  boolean isAlienCharmsAllowedForHero();

  List<CategoryReference> getValidCategoryReferencesForHero();
}
