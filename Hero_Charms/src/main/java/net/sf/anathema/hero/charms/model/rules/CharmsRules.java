package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.magic.charm.Charm;

import java.util.List;

public interface CharmsRules {

  boolean isAllowedAlienCharms(CasteType caste);

  boolean isAlienCharm(Charm charm);

  boolean isAlienCategory(CategoryReference category);

  MartialArtsRules getMartialArtsRules();

  List<CategoryReference> getNativeCategories();

  boolean isCompulsiveCharm(Charm charm);
}