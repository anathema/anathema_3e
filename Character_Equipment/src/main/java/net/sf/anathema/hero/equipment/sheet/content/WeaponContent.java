package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.equipment.sheet.content.stats.EquipmentNameStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.IEquipmentStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapons.AccuracyWeaponStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapons.DamageWeaponStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapons.DefenceWeaponStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapons.OverwhelmingStatGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapons.TagsStatsGroup;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.SubBoxContent;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.library.resources.Resources;

public class WeaponContent extends AbstractEquipmentContent<IWeaponStats> implements SubBoxContent {

  private final EquipmentHeroEvaluator provider;
  private final EquipmentOptionsProvider optionProvider;

  public WeaponContent(Hero hero, Resources resources) {
    super(hero, resources);
    EquipmentModel model = EquipmentModelFetcher.fetch(hero);
    provider = model.getHeroEvaluator();
    optionProvider = model.getOptionProvider();
  }

  @Override
  public IWeaponStats[] getPrintStats() {
    return getEquipmentModel().getPrintWeapons(getResources());
  }

  @SuppressWarnings("unchecked")
  @Override
  public IEquipmentStatsGroup<IWeaponStats>[] createStatsGroups() {
    return new IEquipmentStatsGroup[]{createNameGroup(), createAccuracyGroup(), createDamageGroup(), createOverwhelmingGroup(), createDefenceGroup(), createTagsGroup()};
  }

  private IEquipmentStatsGroup createOverwhelmingGroup() {
    return new OverwhelmingStatGroup(getResources());
  }

  private EquipmentNameStatsGroup<IWeaponStats> createNameGroup() {
    return new EquipmentNameStatsGroup<>(getResources());
  }

  private DamageWeaponStatsGroup createDamageGroup() {
    return new DamageWeaponStatsGroup(getResources(), getOverallTraits());
  }

  private TagsStatsGroup createTagsGroup() {
    return new TagsStatsGroup(getResources());
  }

  private TraitMap getOverallTraits() {
    return TraitModelFetcher.fetch(getHero());
  }
  
  @Override
  public int getLineCount() {
    return 10;
  }

  private DefenceWeaponStatsGroup createDefenceGroup() {
    return new DefenceWeaponStatsGroup(getResources(), getHero(), provider, optionProvider);
  }

  private AccuracyWeaponStatsGroup createAccuracyGroup() {
    return new AccuracyWeaponStatsGroup(getResources(), getOverallTraits(), provider, optionProvider);
  }

  @Override
  public String getHeader() {
    return getResources().getString("Sheet.Header.Weapons");
  }
}