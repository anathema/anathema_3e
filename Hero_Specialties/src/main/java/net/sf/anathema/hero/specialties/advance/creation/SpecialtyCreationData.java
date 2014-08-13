package net.sf.anathema.hero.specialties.advance.creation;

import net.sf.anathema.hero.specialties.template.SpecialtyPointsTemplate;

public class SpecialtyCreationData {

  private SpecialtyPointsTemplate template;

  public SpecialtyCreationData(SpecialtyPointsTemplate template) {
    this.template = template;
  }

  public int getFreebiePoints() {
    return template.freebiePoints;
  }

  public int getBonusPointCostPerDot() {
    return template.bonusPointCost;
  }
}
