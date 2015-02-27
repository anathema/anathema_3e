package net.sf.anathema.hero.thaumaturgy.advance;

import java.util.List;

import net.sf.anathema.hero.thaumaturgy.model.KnownRitual;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyModel;
import net.sf.anathema.points.display.overview.model.AbstractIntegerValueModel;

import static java.util.stream.Collectors.toList;

public class ThaumaturgyExperienceModel extends AbstractIntegerValueModel {

  private final ThaumaturgyModel model;
  
  public final static int BASIC_RITUAL_COST = 3;
  public final static int ADVANCED_RITUAL_COST = 5;

  public ThaumaturgyExperienceModel(String id, ThaumaturgyModel model) {
    super(id, id);
    this.model = model;
  }

  @Override
  public Integer getValue() {
    int totalXP = 0;
    int freeRitualPicks = model.getFreeRitualPicks();
    List<KnownRitual> rituals = model.getKnownTraits();
    rituals = rituals.stream().sorted((r1, r2) -> getXPCostForRitual(r2) - getXPCostForRitual(r1))
    		.collect(toList());
    for (KnownRitual ritual : rituals) {
      if (freeRitualPicks == 0) {
      	totalXP += getXPCostForRitual(ritual);
      } else {
      	freeRitualPicks--;
      }
    }
    return totalXP;
  }
  
  private int getXPCostForRitual(KnownRitual ritual) {
  	switch (ritual.getLevel()) {
    case Basic:
    default:
    	return BASIC_RITUAL_COST;
    case Advanced:
    	return ADVANCED_RITUAL_COST;
  	}
  }
}