package net.sf.anathema.hero.advance.creation;

import net.sf.anathema.hero.points.template.PointsTemplate;

public class PointsCreationData {

  private PointsTemplate template;

  public PointsCreationData(PointsTemplate template) {
    this.template = template;
  }

  public int getBonusPointAllowance() {
    return template.bonusPoints;
  }
}
