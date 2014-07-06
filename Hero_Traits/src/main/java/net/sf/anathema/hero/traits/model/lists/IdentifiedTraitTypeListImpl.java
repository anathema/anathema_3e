package net.sf.anathema.hero.traits.model.lists;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.identifier.Identifier;

public class IdentifiedTraitTypeListImpl extends DefaultTraitTypeList implements IdentifiedTraitTypeList {

  private final Identifier groupId;

  public IdentifiedTraitTypeListImpl(TraitType[] traitTypes, Identifier groupId) {
    super(traitTypes);
    this.groupId = groupId;
  }

  @Override
  public Identifier getListId() {
    return groupId;
  }
}