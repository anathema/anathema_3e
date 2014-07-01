package net.sf.anathema.character.equipment.character.model.stats;

import com.google.common.base.Function;
import net.sf.anathema.character.equipment.creation.model.WeaponTag;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.health.model.HealthType;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public class WeaponStats extends AbstractCombatStats implements IWeaponStats {

  private int accuracy;
  private int damage;
  private String damageTypeString = "Lethal";
  private Integer defence;
  private Integer range;
  private Integer rate;
  private int speed;
  private final List<String> tags = new ArrayList<>();
  private int minimumDamage;

  @Override
  public int getAccuracy() {
    return accuracy;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public int getMinimumDamage() {
    return minimumDamage;
  }

  @Override
  public TraitType getDamageTraitType() {
    return AttributeType.Strength;
  }

  @Override
  public HealthType getDamageType() {
    return HealthType.valueOf(damageTypeString);
  }

  @Override
  public Integer getDefence() {
    return defence;
  }

  @Override
  public Integer getRange() {
    return range;
  }

  @Override
  public Integer getRate() {
    return rate;
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public Identifier[] getTags() {
    String[] tagIds = tags.toArray(new String[tags.size()]);
    return transform(tagIds, WeaponTag.class, WeaponTag::valueOf);
  }

  protected final boolean hasTag(WeaponTag tag) {
    return tags.contains(tag.getId());
  }

  @Override
  public boolean inflictsNoDamage() {
    return false;
  }

  public void setAccuracy(int accuracy) {
    this.accuracy = accuracy;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  public void setMinimumDamage(int damage) {
    this.minimumDamage = damage;
  }

  public void setDamageType(HealthType damageType) {
    this.damageTypeString = damageType.name();
  }

  public void setDefence(Integer defence) {
    this.defence = defence;
  }

  public void setRange(Integer range) {
    this.range = range;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public void addTag(Identifier tag) {
    tags.add(tag.getId());
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
}