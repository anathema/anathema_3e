package net.sf.anathema.hero.sheet.pdf.content.stats;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AggregatedStatsModifiersTest {

  private final AggregatedStatsModifiers modifiers = new AggregatedStatsModifiers();
  private final HeroStatsModifiers part = mock(HeroStatsModifiers.class);

  @Test
  public void MobilityPenaltDefaultsToZero() throws Exception {
    assertThat(modifiers.getMobilityPenalty(), is(0));
  }

  @Test
  public void MobilityPenaltsSumsUpParts() throws Exception {
    when(part.getMobilityPenalty()).thenReturn(6);
    modifiers.add(part);
    assertThat(modifiers.getMobilityPenalty(), is(6));
  }
}