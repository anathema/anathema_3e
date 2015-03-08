package net.sf.anathema.hero.magic.costs;

import net.sf.anathema.hero.magic.advance.costs.CostAnalyzer;
import net.sf.anathema.hero.magic.advance.experience.MagicExperienceData;
import net.sf.anathema.hero.charms.dummy.DummyCharm;
import net.sf.anathema.hero.magic.template.advance.MagicPointsTemplate;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MagicExperienceCostTest {

  private MagicPointsTemplate pointsTemplate = new MagicPointsTemplate();
  private MagicExperienceData experienceCosts = new MagicExperienceData(pointsTemplate);
  private CostAnalyzer costAnalyzerMock = mock(CostAnalyzer.class);
  private DummyCharm charm = new DummyCharm("Charm");

  private void configureCostAnalyzer(boolean favored) {
    when(costAnalyzerMock.isMagicFavored(charm)).thenReturn(favored);
  }

  @Test
  public void calculatesCostsForGeneralCharm() throws Exception {
    pointsTemplate.generalExperiencePoints.costs = 10;
    configureCostAnalyzer(false);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(10));
  }

  @Test
  public void calculatesCostsForFavoredCharm() throws Exception {
    pointsTemplate.favoredExperiencePoints.costs = 8;
    configureCostAnalyzer(true);
    assertThat(experienceCosts.getMagicCosts(charm, costAnalyzerMock), is(8));
  }
}