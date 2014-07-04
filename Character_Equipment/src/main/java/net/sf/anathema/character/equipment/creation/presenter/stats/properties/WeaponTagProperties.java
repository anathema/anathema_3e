package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.character.equipment.creation.presenter.IWeaponTag;
import net.sf.anathema.library.resources.Resources;

public class WeaponTagProperties implements TagProperties<IWeaponTag> {

  private final Resources resources;

  public WeaponTagProperties(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getLabel(IWeaponTag tag) {
    return resources.getString("Weapons.Tags." + tag.getId());
  }

  @Override
  public String getToolTip(IWeaponTag tag) {
    String abbreviation = resources.getString("Weapons.Tags." + tag.getId() + ".Short");
    String explanation = resources.getString("Weapons.Tags." + tag.getId() + ".Tooltip");
    return explanation + " (" + abbreviation + ")";
  }
}