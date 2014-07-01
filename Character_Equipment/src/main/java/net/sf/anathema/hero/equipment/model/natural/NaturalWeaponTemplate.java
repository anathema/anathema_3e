package net.sf.anathema.hero.equipment.model.natural;

import net.sf.anathema.character.equipment.character.model.stats.WeaponStats;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Bashing;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Light;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Natural;

public class NaturalWeaponTemplate implements IEquipmentTemplate {

  private static final WeaponStats Unarmed = new WeaponStats() {
    {
      setName(new SimpleIdentifier("Unarmed"));
      addTag(Natural);
      addTag(Light);
      addTag(Bashing);
    }
  };
  public static final Collection<IEquipmentStats> Third_Edition_Weapons = newArrayList(Unarmed, new Grapple());
  private static final String NATURAL = "Natural";

  @Override
  public String getDescription() {
    return NATURAL;
  }

  @Override
  public Collection<IEquipmentStats> getStatsList() {
    return Third_Edition_Weapons;
  }

  @Override
  public String getName() {
    return NATURAL;
  }

  @Override
  public MaterialComposition getComposition() {
    return MaterialComposition.None;
  }

  @Override
  public MagicalMaterial getMaterial() {
    return null;
  }

  @Override
  public ItemCost getCost() {
    return null;
  }
}