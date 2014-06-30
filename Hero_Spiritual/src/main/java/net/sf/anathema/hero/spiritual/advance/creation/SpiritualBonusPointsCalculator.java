package net.sf.anathema.hero.spiritual.advance.creation;

import net.sf.anathema.hero.points.advance.creation.HeroBonusPointCalculator;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;
import net.sf.anathema.hero.spiritual.model.traits.TraitCollectionUtilities;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

public class SpiritualBonusPointsCalculator implements HeroBonusPointCalculator {

  private final Trait willpower;
  private int willpowerBonusPoints;
  private SpiritualCreationData creationData;

  public SpiritualBonusPointsCalculator(SpiritualTraitModel spiritualTraits, SpiritualCreationData creationData) {
    this.creationData = creationData;
    this.willpower = TraitCollectionUtilities.getWillpower(spiritualTraits);
  }

  @Override
  public void recalculate() {
    willpowerBonusPoints = calculateWillpowerPoints();
  }

  @Override
  public int getBonusPointCost() {
    return willpowerBonusPoints;
  }

  @Override
  public int getBonusPointsGranted() {
    return 0;
  }

  private int calculateWillpowerPoints() {
    int calculationBase = creationData.getCalculationBase(OtherTraitType.Willpower);
    return (willpower.getCreationValue() - calculationBase) * creationData.getWillpowerCost();
  }
}