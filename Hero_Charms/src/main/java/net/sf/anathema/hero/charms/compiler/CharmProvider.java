package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

import java.util.List;

public interface CharmProvider {

  List<CategoryReference> getAllCategories();

  List<Charm> getCharms(CategoryReference category);

  List<ISpecialCharm> getSpecialCharms(CategoryReference category);
}