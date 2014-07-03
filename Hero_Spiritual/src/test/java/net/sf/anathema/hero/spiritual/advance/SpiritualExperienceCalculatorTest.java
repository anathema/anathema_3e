package net.sf.anathema.hero.spiritual.advance;

import net.sf.anathema.hero.spiritual.advance.experience.SpiritualExperienceCalculator;
import net.sf.anathema.hero.spiritual.advance.experience.SpiritualExperienceData;
import net.sf.anathema.hero.traits.advance.MultiplyRatingCost;
import net.sf.anathema.hero.traits.dummy.DummyTrait;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SpiritualExperienceCalculatorTest {

  private SpiritualExperienceData experienceCosts = mock(SpiritualExperienceData.class);
  private SpiritualExperienceCalculator calculator = new SpiritualExperienceCalculator(experienceCosts);

  @Test
  public void testEssenceCosts() throws Exception {
    when(experienceCosts.getEssenceCost()).thenReturn(new MultiplyRatingCost(8));
    assertEquals(16, calculator.getEssenceCosts(DummyTrait.createLearnTrait(OtherTraitType.Essence, 2, 3)));
  }

  @Test
  public void testWillpowerCosts() throws Exception {
    when(experienceCosts.getWillpowerCost()).thenReturn(new MultiplyRatingCost(1));
    assertEquals(3, calculator.getWillpowerCosts(DummyTrait.createLearnTrait(OtherTraitType.Willpower, 3, 4)));
  }
}