package net.sf.anathema.hero.equipment.model.merits;

import net.sf.anathema.equipment.stats.WeaponTag;
import net.sf.anathema.hero.merits.model.mechanics.DetailEntryReference;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;

import java.util.List;
import java.util.Map;

public class WeaponModificationDetail {
  private final MechanicalDetail detail;

  public WeaponModificationDetail(MechanicalDetail detail) {
    this.detail = detail;
  }

  public boolean hasModification(int level, WeaponTag tag) {
    List<Integer> levels = detail.getEntry(new DetailEntryReference("levels"));
    Map<WeaponTag, WeaponTag> transformations = detail.getEntry(new DetailEntryReference("transformations"));
    return appliesToLevel(levels, level) && transformations.containsKey(tag);
  }

  public WeaponTag modify(int level, WeaponTag tag) {
    List<Integer> levels = detail.getEntry(new DetailEntryReference("levels"));
    Map<WeaponTag, WeaponTag> transformations = detail.getEntry(new DetailEntryReference("transformations"));
    if (!appliesToLevel(levels, level)) {
      return tag;
    }
    return transformations.get(tag);
  }

  //TODO: Copied from original code. Is the negation (still) correct?
  private boolean appliesToLevel(List<Integer> levels, Integer level) {
    return levels == null || !levels.contains(level);
  }
}