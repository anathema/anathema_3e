package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.magic.charm.Charm;

import java.util.List;

public interface CharmCache extends CharmProvider, CharmIdMap, ExtensibleDataSet {

  Charm getCharmById(CharmName charmId);

  List<CategoryReference> getAllCategories();

  Charm[] getCharms(CategoryReference category);

  ISpecialCharm[] getSpecialCharms(CategoryReference category);
}