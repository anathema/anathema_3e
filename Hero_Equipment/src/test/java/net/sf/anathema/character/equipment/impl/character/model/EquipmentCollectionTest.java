package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.ItemStatsSet;
import net.sf.anathema.equipment.stats.impl.ArtifactStats;
import net.sf.anathema.hero.equipment.model.EquipmentCollection;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EquipmentCollectionTest {
  private EquipmentCollection collection = new EquipmentCollection();

  @Test
  public void containsAddedItems() throws Exception {
    IEquipmentItem item = createItemWithStats(createStats());
    collection.add(item);
    assertThat(collection.contains(item), is(true));    
  }

  private IEquipmentItem createItemWithStats(ArtifactStats artifactStats) {
    IEquipmentItem item = mock(IEquipmentItem.class);
    when(item.getStats()).thenReturn(ItemStatsSet.withSingleStat(artifactStats));
    when(item.isPrintEnabled(artifactStats)).thenReturn(true);
    return item;
  }

  private ArtifactStats createStats() {
    ArtifactStats artifactStats = new ArtifactStats();
    artifactStats.setAttuneCost(3);
    return artifactStats;
  }
}