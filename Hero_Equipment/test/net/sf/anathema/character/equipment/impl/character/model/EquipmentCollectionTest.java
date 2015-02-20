package net.sf.anathema.character.equipment.impl.character.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IWeaponModifiers;
import net.sf.anathema.equipment.stats.ItemStatsSet;
import net.sf.anathema.equipment.stats.impl.TraitModifyingStats;
import net.sf.anathema.hero.equipment.model.EquipmentCollection;

import org.junit.Test;

public class EquipmentCollectionTest {
  private EquipmentCollection collection = new EquipmentCollection();

  @Test
  public void createsModifiers() throws Exception {
    TraitModifyingStats traitModifyingStats = createStats();
    IEquipmentItem item = createItemWithStats(traitModifyingStats);
    collection.add(item);
    IWeaponModifiers modifiers = collection.createModifiers();
    assertThat(modifiers.getMeleeAccuracyMod(), is(5));
  }

  @Test
  public void containsAddedItems() throws Exception {
    IEquipmentItem item = createItemWithStats(createStats());
    collection.add(item);
    assertThat(collection.contains(item), is(true));    
  }

  private IEquipmentItem createItemWithStats(TraitModifyingStats traitModifyingStats) {
    IEquipmentItem item = mock(IEquipmentItem.class);
    when(item.getStats()).thenReturn(ItemStatsSet.withSingleStat(traitModifyingStats));
    when(item.isPrintEnabled(traitModifyingStats)).thenReturn(true);
    return item;
  }

  private TraitModifyingStats createStats() {
    TraitModifyingStats traitModifyingStats = new TraitModifyingStats();
    traitModifyingStats.setMeleeAccuracyMod(5);
    return traitModifyingStats;
  }
}