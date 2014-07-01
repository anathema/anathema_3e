package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.TreeCategoryReference;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.template.NativeCharacterType;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.MARTIAL_ARTS;
import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.getTreeCategory;

public class CharmOptionRulesImpl implements CharmOptionRules {

  private final MartialArtsLevel standardLevel;
  private Hero hero;
  private CharacterTypes characterTypes;

  public CharmOptionRulesImpl(Hero hero, CharmsRules rules, CharacterTypes characterTypes) {
    this.hero = hero;
    this.characterTypes = characterTypes;
    standardLevel = rules.getMartialArtsRules().getStandardLevel();
  }

  @Override
  public boolean isValidOptionForHeroesType(Charm charm) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(charm);
    if (level != null) {
      return level.compareTo(standardLevel) <= 1;
    }
    return true;
  }

  @Override
  public List<TreeCategoryReference> getAllCategories() {
    List<TreeCategoryReference> categories = new ArrayList<>();
    for(CharacterType type: characterTypes) {
      categories.add(getTreeCategory(type));
    }
    categories.add(getTreeCategory(MARTIAL_ARTS));
    return categories;
  }

  @Override
  public List<TreeCategoryReference> getNativeCategories() {
    TreeCategoryReference typeCategory = getTreeCategory(getNativeCharacterType());
    TreeCategoryReference martialArtsCategory = getTreeCategory(MARTIAL_ARTS);
    return asList(typeCategory, martialArtsCategory);
  }

  private CharacterType getNativeCharacterType() {
    return NativeCharacterType.get(hero);
  }
}
