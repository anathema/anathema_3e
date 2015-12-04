package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CategoryReference;
import net.sf.anathema.hero.concept.model.concept.CasteType;

import java.util.List;

public interface CharmsRules {

  boolean isAllowedAlienCharms(CasteType caste);

  boolean isAlienCharm(Charm charm);

  boolean isAlienCategory(CategoryReference category);

  List<CategoryReference> getNativeCategories();

  /** Whether there are any charms the splat could conceivably learn, given appropriate selected options (such as caste). */
  boolean isAllowedAnyCharms();
}