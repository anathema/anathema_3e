package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.MARTIAL_ARTS;

public class CharmOptionRulesImpl implements CharmOptionRules {

  private final MartialArtsLevel standardLevel;
  private CharmsRules rules;
  private CharacterTypes characterTypes;

  public CharmOptionRulesImpl(CharmsRules rules, CharacterTypes characterTypes) {
    this.rules = rules;
    this.characterTypes = characterTypes;
    standardLevel = rules.getMartialArtsRules().getStandardLevel();
  }

  @Override
  public boolean isValidOptionForHeroType(Charm charm) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(charm);
    if (level != null) {
      return level.compareTo(standardLevel) <= 1;
    }
    return true;
  }

  @Override
  // todo sandra: collect from all charms
  public List<CategoryReference> getAllCategories() {
    List<CategoryReference> categories = new ArrayList<>();
    for(CharacterType type: characterTypes) {
      categories.add(MartialArtsUtilities.getCategory(type));
    }
    categories.add(MartialArtsUtilities.getCategory(MARTIAL_ARTS));
    return categories;
  }

  @Override
  public List<CategoryReference> getNativeCategories() {
    List<CategoryReference> nativeCategories = new ArrayList<>();
    for(CategoryReference category : getAllCategories()) {
      if (rules.isNative(category)) {
        nativeCategories.add(category);
      }
    }
    return nativeCategories;
  }
}
