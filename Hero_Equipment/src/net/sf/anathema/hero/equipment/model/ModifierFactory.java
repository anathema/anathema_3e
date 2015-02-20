package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.equipment.stats.IWeaponModifiers;

public interface ModifierFactory {

  IWeaponModifiers createModifiers();
}