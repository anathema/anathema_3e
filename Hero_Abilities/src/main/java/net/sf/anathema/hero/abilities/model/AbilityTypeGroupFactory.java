package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.traits.model.group.AbstractTraitTypeGroupFactory;
import net.sf.anathema.library.identifier.Identifier;

public class AbilityTypeGroupFactory extends AbstractTraitTypeGroupFactory {

  @Override
  protected Identifier getGroupIdentifier(String groupId) {
    return AbilityGroupType.valueOf(groupId);
  }
}