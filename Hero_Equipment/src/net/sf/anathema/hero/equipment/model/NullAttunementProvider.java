package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.ArtifactAttuneType;
import net.sf.anathema.hero.equipment.ItemAttunementEvaluator;

public class NullAttunementProvider implements ItemAttunementEvaluator {
  @Override
  public ArtifactAttuneType[] getAttuneTypes(IEquipmentItem item) {
    return new ArtifactAttuneType[0];
  }
}