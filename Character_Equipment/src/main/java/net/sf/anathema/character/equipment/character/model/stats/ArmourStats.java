package net.sf.anathema.character.equipment.character.model.stats;

import net.sf.anathema.character.equipment.creation.model.ArmourTag;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.equipment.character.model.stats.ArmorStatisticsTable.forArtifactArmor;
import static net.sf.anathema.character.equipment.character.model.stats.ArmorStatisticsTable.forMundaneArmor;
import static net.sf.anathema.character.equipment.creation.model.ArmourTag.Artifact;
import static net.sf.anathema.character.equipment.creation.model.ArmourTag.Heavy;
import static net.sf.anathema.character.equipment.creation.model.ArmourTag.Light;
import static net.sf.anathema.character.equipment.creation.model.ArmourTag.Medium;

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
  public Identifier[] getTags() {
    return tags.toArray(new Identifier[tags.size()]);
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