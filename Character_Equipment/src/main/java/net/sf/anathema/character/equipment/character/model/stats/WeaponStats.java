package net.sf.anathema.character.equipment.character.model.stats;

import net.sf.anathema.character.equipment.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTag;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.hero.health.model.HealthType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.library.identifier.Identifier;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.equipment.character.model.stats.WeaponStatisticsTable.forArtifactWeapons;
import static net.sf.anathema.character.equipment.character.model.stats.WeaponStatisticsTable.forMundaneWeapons;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Artifact;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Heavy;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Lethal;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Light;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Medium;

public class WeaponStats extends AbstractCombatStats implements IWeaponStats {

  private final List<IWeaponTag> tags = new ArrayList<>();

  @Override
  public int getAccuracy() {
    return getWeaponStatsEntry().getAccuracy();
  }

  @Override
  public int getDamage() {
    return getWeaponStatsEntry().getDamage();
  }

  @Override
  public TraitType getDamageTraitType() {
    return AttributeType.Strength;
  }

  @Override
  public HealthType getDamageType() {
    if (hasTag(Lethal)) {
      return HealthType.Lethal;
    }
    else {
      return HealthType.Bashing;
    }
  }

  @Override
  public Integer getDefence() {
    return getWeaponStatsEntry().getDefense();
  }

  @Override
  public Identifier[] getTags() {
    return tags.toArray(new IWeaponTag[tags.size()]);
  }

  protected final boolean hasTag(WeaponTag tag) {
    return tags.contains(tag);
  }

  @Override
  public boolean inflictsNoDamage() {
    return false;
  }

  public void addTag(IWeaponTag tag) {
    tags.add(tag);
  }

  @Override
  public boolean isRangedCombat() {
    for (WeaponTag tag : WeaponTag.getRangedWeaponTypeTags()) {
      if (hasTag(tag)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getId() {
    return getName().getId();
  }

  @Override
  public AbilityType getTraitType() {
    return isMartialArtsOnlyWeapon() ? AbilityType.MartialArts : AbilityType.Melee;
  }

  private boolean isMartialArtsOnlyWeapon() {
    return hasTag(WeaponTag.Natural);
  }

  @Override
  public int getMobilityPenalty() {
    return 0;
  }

  @Override
  public IEquipmentStats[] getViews() {
    if (isMartialArtsOnlyWeapon()) {
      return new IEquipmentStats[]{this};
    }
    IEquipmentStats[] views = new IEquipmentStats[2];
    views[0] = new WeaponStatsDecorator(this, AbilityType.Melee);
    views[1] = new WeaponStatsDecorator(this, AbilityType.MartialArts);
    return views;
  }

  @Override
  public int getOverwhelmingValue() {
    return getWeaponStatsEntry().getOverwhelming();
  }

  private WeaponStatisticsTable.WeaponStatisticsEntry getWeaponStatsEntry() {
    if (isArtifact()) {
      return forArtifactWeapons().forSize(getSize());
    } else {
      return forMundaneWeapons().forSize(getSize());
    }
  }

  private boolean isArtifact() {
    return hasTag(Artifact);
  }

  private WeaponTag getSize() {
    if (hasTag(Light)) {
      return Light;
    }
    if (hasTag(Medium)) {
      return Medium;
    }
    return Heavy;
  }
}