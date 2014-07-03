package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.traits.model.lists.DefaultTraitTypeList;
import net.sf.anathema.hero.traits.model.types.AbilityType;

public class AllAbilityTraitTypeList extends DefaultTraitTypeList {

  private static final AllAbilityTraitTypeList instance = new AllAbilityTraitTypeList();

  public static AllAbilityTraitTypeList getInstance() {
    return instance;
  }

  private AllAbilityTraitTypeList() {
    super(AbilityType.values());
  }
}