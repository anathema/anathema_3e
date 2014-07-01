package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.options.CharmOptionCheck;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.magic.charm.Charm;

import java.util.List;

public interface CharmProvider {

  List<CategoryReference> getAllCategories();

  Charm[] getCharms(CategoryReference category);

  ISpecialCharm[] getSpecialCharms(CategoryReference categoryReference);

  ISpecialCharm[] getSpecialCharms(CharmOptionCheck optionCheck, CharmIdMap map, CategoryReference preferredCategory);
}