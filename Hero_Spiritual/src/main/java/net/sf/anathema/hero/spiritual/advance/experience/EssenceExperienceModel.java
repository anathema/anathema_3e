package net.sf.anathema.hero.spiritual.advance.experience;

import net.sf.anathema.hero.points.display.overview.model.AbstractIntegerValueModel;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

public class EssenceExperienceModel extends AbstractIntegerValueModel {
  private final SpiritualTraitModel spiritualTraits;
  private final SpiritualExperienceCalculator calculator;

  public EssenceExperienceModel(SpiritualTraitModel spiritualTraits, SpiritualExperienceCalculator calculator) {
    super("Experience", "Essence");
    this.spiritualTraits = spiritualTraits;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    Trait essence = spiritualTraits.getTrait(OtherTraitType.Essence);
    return calculator.getEssenceCosts(essence);
  }
}