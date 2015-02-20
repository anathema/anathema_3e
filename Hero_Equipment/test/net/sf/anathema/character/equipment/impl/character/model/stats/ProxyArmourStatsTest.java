package net.sf.anathema.character.equipment.impl.character.model.stats;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.sf.anathema.equipment.stats.impl.ArmourStats;
import net.sf.anathema.equipment.stats.impl.ArmourTag;
import net.sf.anathema.equipment.stats.impl.ProxyArmourStats;

import org.junit.Test;

public class ProxyArmourStatsTest {

  @Test
  public void hasOriginalMobilityPenaltyWithoutMagicalMaterial() throws Exception {
    ArmourStats original = createArmorWithMobilityPenalty();
    ProxyArmourStats stats = new ProxyArmourStats(original);
    assertThat(stats.getMobilityPenalty(), is(-2));
  }

  @Test
  public void hasNoMobilityPenaltyWithMoonsilver() throws Exception {
    ArmourStats original = createArmorWithMobilityPenalty();
    ProxyArmourStats stats = new ProxyArmourStats(original);
    assertThat(stats.getMobilityPenalty(), is(0));
  }

  @Test
  public void hasImprovedMobilityPenaltyWithAdamant() throws Exception {
    ArmourStats original = createArmorWithMobilityPenalty();
    ProxyArmourStats stats = new ProxyArmourStats(original);
    assertThat(stats.getMobilityPenalty(), is(-1));
  }

  private ArmourStats createArmorWithMobilityPenalty() {
    ArmourStats original = new ArmourStats();
    original.addTag(ArmourTag.Heavy);
    return original;
  }
}