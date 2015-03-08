package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CategoryReference;

import java.util.List;

public interface CharmOptionRules extends CharmOptionCheck {

  boolean isValidOptionForHeroType(Charm charm);

  List<CategoryReference> getAllCategories();

  List<CategoryReference> getNativeCategories();
}
