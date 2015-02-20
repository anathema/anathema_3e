package net.sf.anathema.equipment.editor.stats.model.impl;


public class ArmourStatisticsModel extends EquipmentStatisticsModel {
  private ArmourTagsModel model;

  public ArmourStatisticsModel(ArmourTagsModel model) {
    this.model = model;
  }

  @Override
  public boolean isValid() {
    return super.isValid() && model.isSizeSelected();
  }
}