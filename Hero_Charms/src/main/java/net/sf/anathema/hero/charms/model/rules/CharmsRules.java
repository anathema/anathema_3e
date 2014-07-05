package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.concept.model.concept.CasteType;

import java.util.List;
import java.util.function.Consumer;

public interface CharmsRules {

  boolean isAllowedAlienCharms(CasteType caste);

  boolean isAlienCharm(Charm charm);

  boolean isAlienCategory(CategoryReference category);

  MartialArtsRules getMartialArtsRules();

  List<CategoryReference> getNativeCategories();

  boolean isCompulsiveCharm(Charm charm);

  void forAllCompulsiveCharms(Consumer<? super CharmName> consumer);
}