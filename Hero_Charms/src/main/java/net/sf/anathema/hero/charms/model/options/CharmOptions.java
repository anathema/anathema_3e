package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.display.presenter.CharmTreeArbitrator;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.magic.charm.Charm;

import java.util.List;

public interface CharmOptions extends CharmTreeArbitrator {

  CharmIdMap getCharmIdMap();

  ISpecialCharm[] getSpecialCharms();

  Charm[] filterAvailableCharms(CharmTree tree);

  boolean isAlienCharmsAllowedForHero();

  List<CategoryReference> getValidCategoryReferencesForHero();
}
