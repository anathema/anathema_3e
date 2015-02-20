package net.sf.anathema.hero.equipment.model.natural;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;

import net.sf.anathema.equipment.character.IEquipmentTemplate;
import net.sf.anathema.equipment.character.ItemCost;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.impl.WeaponStats;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import static net.sf.anathema.equipment.stats.impl.WeaponTag.Natural;
import static net.sf.anathema.equipment.stats.impl.WeaponTag.Light;
import static net.sf.anathema.equipment.stats.impl.WeaponTag.Bashing;

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
  public ItemCost getCost() {
    return null;
  }
}