package net.sf.anathema.hero.magic.costs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import net.sf.anathema.hero.charms.advance.costs.CostAnalyzer;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationData;
import net.sf.anathema.hero.charms.dummy.DummyCharm;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;
import net.sf.anathema.magic.data.Magic;

public class MagicCreationCostTest {

  private MagicPointsTemplate template = new MagicPointsTemplate();
  private DummyCharm charm = new DummyCharm("test");
  private CostAnalyzer costAnalyzerMock = mock(CostAnalyzer.class);

  private void assertCharmCosts(int expectedValue) {
    MagicCreationData magicCosts = new MagicCreationData(template);
    int charmCost = magicCosts.getMagicCosts(charm, costAnalyzerMock);
    assertThat(expectedValue, is(charmCost));
  }

  private void configureCostAnalyzer(boolean favored) {
    when(costAnalyzerMock.isMagicFavored((Magic) any())).thenReturn(favored);
  }
}
