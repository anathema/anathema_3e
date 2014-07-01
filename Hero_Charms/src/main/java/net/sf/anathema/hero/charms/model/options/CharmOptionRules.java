package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.CategoryReference;

import java.util.List;

public interface CharmOptionRules extends CharmOptionCheck {

  // boolean isValidOptionForHero(Charm charm);

  List<CategoryReference> getAllCategories();

  List<CategoryReference> getNativeCategories();
}
