package net.sf.anathema.equipment.editor.stats.model.impl;

import net.sf.anathema.equipment.editor.stats.model.IArtifactStatisticsModel;
import net.sf.anathema.equipment.editor.stats.model.IIntValueModel;
import net.sf.anathema.library.number.Range;

public class ArtifactStatisticsModel extends EquipmentStatisticsModel implements IArtifactStatisticsModel {
  private final IIntValueModel attuneCostModel = new RangedIntValueModel(Range.boundedFromBelow(0), 0);

  @Override
  public IIntValueModel getAttuneCostModel() {
    return attuneCostModel;
  }
}
