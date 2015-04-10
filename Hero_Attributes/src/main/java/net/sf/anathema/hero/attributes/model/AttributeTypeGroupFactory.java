package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.hero.traits.model.group.AbstractTraitTypeGroupFactory;
import net.sf.anathema.hero.traits.model.types.AttributeGroupType;
import net.sf.anathema.library.identifier.Identifier;

public class AttributeTypeGroupFactory extends AbstractTraitTypeGroupFactory {

  @Override
  protected Identifier getGroupIdentifier(String groupId) {
    return AttributeGroupType.valueOf(groupId);
  }
}