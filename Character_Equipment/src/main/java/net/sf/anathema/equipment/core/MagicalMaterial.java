package net.sf.anathema.equipment.core;

import net.sf.anathema.library.identifier.Identifier;

public enum MagicalMaterial implements Identifier {
  Orichalcum, Jade, Moonsilver, Starmetal, Soulsteel, Adamant,
  VitriolOrichalcum, VitriolJade, VitriolMoonsilver, VitriolStarmetal, VitriolSoulsteel, VitriolAdamant;

  @Override
  public String getId() {
    return name();
  }
}