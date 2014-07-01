package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.magic.charm.Charm;

import java.util.List;

public interface CharmOptionRules extends CharmOptionCheck {

  boolean isValidOptionForHeroType(Charm charm);

  List<CategoryReference> getAllCategories();

  List<CategoryReference> getNativeCategories();
}
