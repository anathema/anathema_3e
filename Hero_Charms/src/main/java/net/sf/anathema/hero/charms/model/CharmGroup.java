package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.lib.util.Identifier;

public class CharmGroup implements ICharmGroup, Identifier {

  private final String id;
  private final Charm[] charms;
  private final CharacterType type;

  public CharmGroup(CharacterType type, String id, Charm[] charms) {
    this.id = id;
    this.type = type;
    this.charms = charms;
  }

  @Override
  public final String getId() {
    return id;
  }

  @Override
  public String toString() {
    return id;
  }

  @Override
  public Charm[] getAllCharms() {
    return charms;
  }

  @Override
  public CharacterType getCharacterType() {
    return type;
  }

  @Override
  public boolean isCharmFromGroup(Charm charm) {
    boolean isOfGroupType =  charm.getCharacterType().equals(type);
    boolean isFromGroupWithId = charm.getGroupId().equals(id);
    return isOfGroupType && isFromGroupWithId;
  }
}