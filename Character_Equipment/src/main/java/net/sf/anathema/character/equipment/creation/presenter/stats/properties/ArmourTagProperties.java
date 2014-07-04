package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.character.equipment.creation.model.ArmourTag;
import net.sf.anathema.library.resources.Resources;

public class ArmourTagProperties implements TagProperties<ArmourTag> {
  private Resources resources;

  public ArmourTagProperties(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getLabel(ArmourTag armourTag) {
    return resources.getString("Armour.Tags."+armourTag.getId());
  }

  @Override
  public String getToolTip(ArmourTag armourTag) {
    String abbreviation = resources.getString("Weapons.Tags." + armourTag.getId() + ".Short");
    String explanation = resources.getString("Weapons.Tags." + armourTag.getId() + ".Tooltip");
    return explanation + " (" + abbreviation + ")";
  }
}