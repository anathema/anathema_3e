package net.sf.anathema.equipment.editor.stats.model.impl;

import java.util.Collection;

import net.sf.anathema.equipment.editor.stats.model.EquipmentStatisticsType;
import net.sf.anathema.equipment.editor.stats.model.EquipmentStatsFactory;
import net.sf.anathema.equipment.editor.stats.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.equipment.editor.stats.model.IEquipmentStatisticsModel;
import net.sf.anathema.equipment.editor.stats.model.ModelToStats;
import net.sf.anathema.equipment.stats.IEquipmentStats;

public class SimpleEquipmentStatsFactory implements EquipmentStatsFactory {

  private final ModelToStats modelToStats = new ModelToStats();

  @Override
  public IEquipmentStats createNewStats(Collection<String> definedNames, String nameProposal, EquipmentStatisticsType type) {
    IEquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel();
    model.setForbiddenNames(definedNames);
    model.setEquipmentType(type);
    String finalName = createUniqueName(nameProposal, model);
    setNameOnCorrectModel(model, finalName);
    return modelToStats.createStats(model);
  }

  private void setNameOnCorrectModel(IEquipmentStatisticsCreationModel model, String finalName) {
    findMatchingModel(model).getName().setText(finalName);
  }

  private IEquipmentStatisticsModel findMatchingModel(IEquipmentStatisticsCreationModel model) {
    switch (model.getEquipmentType()) {
      case Weapon:
        return model.getWeaponModel();
      case Armor:
        return model.getArmorModel();
      case Artifact:
        return model.getArtifactStatisticsModel();
      default:
        throw new IllegalStateException("Type not seleted.");
    }
  }

  private String createUniqueName(String nameProposal, IEquipmentStatisticsCreationModel model) {
    int count = 1;
    String finalName = nameProposal;
    while (!model.isNameUnique(finalName)) {
      count++;
      finalName = nameProposal + " " + count;
    }
    return finalName;
  }
}