package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.concept.model.concept.CasteType;

import java.util.List;

public interface CharmsRules {

  boolean isAllowedAlienCharms(CasteType caste);

  boolean isAlienCharm(Charm charm);

  boolean isAlienCategory(CategoryReference category);

  List<CategoryReference> getNativeCategories();
}