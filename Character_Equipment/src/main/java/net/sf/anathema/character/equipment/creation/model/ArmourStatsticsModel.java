package net.sf.anathema.character.equipment.creation.model;

public class ArmourStatsticsModel extends EquipmentStatisticsModel {
  private ArmourTagsModel model;

  public ArmourStatsticsModel(ArmourTagsModel model) {
    this.model = model;
  }

  @Override
  public boolean isValid() {
    return super.isValid() && model.isSizeSelected();
  }
}