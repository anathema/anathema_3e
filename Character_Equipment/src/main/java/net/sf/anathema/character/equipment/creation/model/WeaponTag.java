package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IWeaponTag;

public enum WeaponTag implements IWeaponTag {

  Light, Medium, Heavy, Lethal, Bashing, Artifact, Shield, Balanced, Archery, Thrown, CloseRange, ShortRange, MediumRange, LongRange, Flame, Natural;

  @Override
  public String getId() {
    return name();
  }

  public static WeaponTag[] getSizeTags() {
    return new WeaponTag[]{Light, Medium, Heavy};
  }

  public static WeaponTag[] getDamageTags() {
    return new WeaponTag[]{Lethal, Bashing};
  }

  public static WeaponTag[] getRangedWeaponTypeTags() {
    return new WeaponTag[]{Archery, Thrown};
  }

  public static WeaponTag[] getRangedWeaponExclusiveTags() {
    return new WeaponTag[]{CloseRange, ShortRange, MediumRange, LongRange, Flame};
  }

  public static WeaponTag[] getRangeTags() {
    return new WeaponTag[]{CloseRange, ShortRange, MediumRange, LongRange};
  }
}