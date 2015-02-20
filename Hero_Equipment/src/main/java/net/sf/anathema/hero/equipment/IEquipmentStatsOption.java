package net.sf.anathema.hero.equipment;

import net.sf.anathema.hero.specialties.model.Specialty;

public interface IEquipmentStatsOption {

  String getName();

  String getType();

  int getAccuracyModifier();

  int getDefenseModifier();

  Specialty getUnderlyingTrait();
}
