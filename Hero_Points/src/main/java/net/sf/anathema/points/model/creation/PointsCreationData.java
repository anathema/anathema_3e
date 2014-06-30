package net.sf.anathema.points.model.creation;

import net.sf.anathema.points.template.PointsTemplate;

public class PointsCreationData {

  private PointsTemplate template;

  public PointsCreationData(PointsTemplate template) {
    this.template = template;
  }

  public int getBonusPointAllowance() {
    return template.bonusPoints;
  }
}
