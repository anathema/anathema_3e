package net.sf.anathema.hero.thaumaturgy.advance;

import net.sf.anathema.hero.thaumaturgy.model.KnownRitual;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyModel;
import net.sf.anathema.points.display.overview.model.AbstractIntegerValueModel;

public class ThaumaturgyExperienceModel extends AbstractIntegerValueModel {

  private final ThaumaturgyModel model;
  
  private final int BASIC_RITUAL_COST = 3;
  private final int ADVANCED_RITUAL_COST = 5;

  public ThaumaturgyExperienceModel(String id, ThaumaturgyModel model) {
    super(id, id);
    this.model = model;
  }

  @Override
  public Integer getValue() {
    int totalXP = 0;
    for (KnownRitual ritual : model.getKnownRituals()) {
      switch (ritual.getLevel()) {
        case Basic:
        	totalXP += BASIC_RITUAL_COST;
        	break;
        case Advanced:
        	totalXP += ADVANCED_RITUAL_COST;
        	break;
      }
    }
    return totalXP;
  }
}