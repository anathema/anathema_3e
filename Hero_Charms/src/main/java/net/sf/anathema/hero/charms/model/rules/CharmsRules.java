package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.concept.CasteType;

import java.util.List;

public interface CharmsRules {

  boolean isAllowedAlienCharms(CasteType caste);

  MartialArtsRules getMartialArtsRules();

  List<CategoryReference> getNativeCategories();
}