package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.magic.charm.Charm;

public interface CharmCache extends ExtensibleDataSet, CharmIdMap {

  CategoryReference[] getCharmCategories();

  ISpecialCharm[] getSpecialCharmData(CategoryReference category);

  Charm[] getCharms(CategoryReference category);

  CharmProvider getCharmProvider();
}