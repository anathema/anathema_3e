package net.sf.anathema.hero.equipment;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.ArtifactAttuneType;

public interface ItemAttunementEvaluator {
  ArtifactAttuneType[] getAttuneTypes(IEquipmentItem item);
}