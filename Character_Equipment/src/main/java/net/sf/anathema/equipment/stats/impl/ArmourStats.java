package net.sf.anathema.equipment.stats.impl;

import static net.sf.anathema.character.equipment.character.model.stats.ArmorStatisticsTable.forArtifactArmor;
import static net.sf.anathema.character.equipment.character.model.stats.ArmorStatisticsTable.forMundaneArmor;
import static net.sf.anathema.equipment.stats.impl.ArmourTag.Artifact;
import static net.sf.anathema.equipment.stats.impl.ArmourTag.Heavy;
import static net.sf.anathema.equipment.stats.impl.ArmourTag.Light;
import static net.sf.anathema.equipment.stats.impl.ArmourTag.Medium;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.stats.ArmorStatisticsTable;
import net.sf.anathema.equipment.stats.IArmourStats;
import net.sf.anathema.library.identifier.Identifier;

public class ArmourStats extends AbstractCombatStats implements IArmourStats {

  private final List<ArmourTag> tags = new ArrayList<>();

  @Override
  public Integer getHardness() {
    return getArmorStatsEntry().getHardness();
  }

  @Override
  public Integer getMobilityPenalty() {
    return getArmorStatsEntry().getMobilityPenalty();
  }

  @Override
  public Integer getSoak() {
    return getArmorStatsEntry().getSoak();
  }

  @Override
  public Collection<Identifier> getTags() {
    return new ArrayList<>(tags);
  }

  @Override
  public String getId() {
    return getName().getId();
  }

  public void addTag(ArmourTag tag) {
    tags.add(tag);
  }

  private ArmorStatisticsTable.ArmorStatisticsEntry getArmorStatsEntry() {
    if (isArtifact()) {
      return forArtifactArmor().forSize(getSize());
    } else {
      return forMundaneArmor().forSize(getSize());
    }
  }

  private boolean isArtifact() {
    return hasTag(Artifact);
  }

  private ArmourTag getSize() {
    if (hasTag(Light)) {
      return Light;
    }
    if (hasTag(Medium)) {
      return Medium;
    }
    return Heavy;
  }

  private boolean hasTag(ArmourTag tag) {
    return tags.contains(tag);
  }
}