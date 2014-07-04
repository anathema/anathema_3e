package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.character.equipment.character.model.stats.AbstractCombatStats;
import net.sf.anathema.hero.equipment.model.natural.NaturalSoak;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class EffectiveArmour extends AbstractCombatStats implements IArmourStats {

  private int mobilityPenalty = 0;
  private int naturalSoak = 0;
  private int equipmentSoak = 0;
  private int hardness = 0;

  @Override
  public Integer getHardness() {
    return hardness;
  }

  @Override
  public Integer getMobilityPenalty() {
    return mobilityPenalty;
  }

  @Override
  public Integer getSoak() {
    return naturalSoak + equipmentSoak;
  }

  @Override
  public Identifier[] getTags() {
    return new Identifier[0];
  }

  @Override
  public Identifier getName() {
    return new SimpleIdentifier("EffectiveArmour");
  }

  public void addArmour(IArmourStats armour) {
    if (armour instanceof NaturalSoak) {
      handleNaturalArmour(armour);
    } else {
      handleEquipmentArmour(armour);
    }
  }

  private void handleNaturalArmour(IArmourStats armour) {
    naturalSoak = armour.getSoak();
  }

  private void handleEquipmentArmour(IArmourStats armour) {
    modifyMobilityPenalty(armour.getMobilityPenalty());
    equipmentSoak = getHighestValue(equipmentSoak, armour.getSoak());
    hardness = getHighestValue(hardness, armour.getHardness());
  }

  public void modifyMobilityPenalty(Integer amount) {
    if (amount != null) {
      mobilityPenalty += amount;
    }
  }

  private int getHighestValue(int currentValue, Integer newValue) {
    if (newValue == null) {
      return currentValue;
    }
    return newValue > currentValue ? newValue : currentValue;
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}