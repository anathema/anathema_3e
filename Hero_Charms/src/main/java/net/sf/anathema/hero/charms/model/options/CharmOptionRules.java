package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.TreeCategoryReference;

import java.util.List;

public interface CharmOptionRules extends CharmOptionCheck {

  // boolean isValidOptionForHero(Charm charm);

  List<TreeCategoryReference> getAllCategories();

  List<TreeCategoryReference> getNativeCategories();
}
