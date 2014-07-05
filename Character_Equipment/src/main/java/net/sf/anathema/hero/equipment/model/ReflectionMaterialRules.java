package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.environment.herotype.HeroTypeSpecificsMap;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactAttuneType;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.library.initialization.ObjectFactory;

public class ReflectionMaterialRules implements MaterialRules {
  private final HeroTypeSpecificsMap<CharacterTypeMaterialRules> map;

  public ReflectionMaterialRules(ObjectFactory objectFactory) {
    this.map = new HeroTypeSpecificsMap<>(objectFactory, CharacterTypeMaterialRules.class, new DefaultMaterialRules());
  }

  public MagicalMaterial getDefault(HeroType heroType) {
    CharacterTypeMaterialRules rules = map.getForCharacterType(heroType);
    return rules.getDefault();
  }

  public ArtifactAttuneType[] getAttunementTypes(HeroType heroType,
                                                 MagicalMaterial material) {
    CharacterTypeMaterialRules rules = map.getForCharacterType(heroType);
    return rules.getAttunementTypes(material);
  }

  public boolean canAttuneToMalfeanMaterials(HeroType heroType) {
    CharacterTypeMaterialRules rules = map.getForCharacterType(heroType);
    return rules.canAttuneToMalfeanMaterials();
  }
}