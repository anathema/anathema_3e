package net.sf.anathema.hero.spiritual.advance.creation;

import net.sf.anathema.hero.spiritual.template.points.SpiritualPointsTemplate;
import net.sf.anathema.hero.traits.model.TraitType;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Willpower;

public class DefaultSpiritualCreationData implements SpiritualCreationData {

  private SpiritualPointsTemplate template;

  public DefaultSpiritualCreationData(SpiritualPointsTemplate template) {
    this.template = template;
  }

  @Override
  public int getCalculationBase(TraitType type) {
    if (type == Willpower) {
      return template.willpower.calculation.calculationBase;
    }
    return 1;
  }

  @Override
  public int getWillpowerCost() {
    return template.willpower.cost.bonusPoints;
  }
}