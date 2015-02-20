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
  public void DdvDefaultsToZero() throws Exception {
    assertThat(modifiers.getDDVPoolMod(), is(0));
  }

  @Test
  public void DdvSumsUpParts() throws Exception {
    when(part.getDDVPoolMod()).thenReturn(1);
    modifiers.add(part);
    assertThat(modifiers.getDDVPoolMod(), is(1));
  }

  @Test
  public void JoinBattleDefaultsToZero() throws Exception {
    assertThat(modifiers.getJoinBattleMod(), is(0));
  }

  @Test
  public void JoinBattleSumsUpParts() throws Exception {
    when(part.getJoinBattleMod()).thenReturn(2);
    modifiers.add(part);
    assertThat(modifiers.getJoinBattleMod(), is(2));
  }
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