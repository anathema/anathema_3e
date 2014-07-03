package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;

import java.util.List;

public interface CharmCache extends CharmProvider, CharmMap, ExtensibleDataSet {

  Charm getCharmById(CharmName charmId);

  List<CategoryReference> getAllCategories();

  Charm[] getCharms(CategoryReference category);

  ISpecialCharm[] getSpecialCharms(CategoryReference category);
}