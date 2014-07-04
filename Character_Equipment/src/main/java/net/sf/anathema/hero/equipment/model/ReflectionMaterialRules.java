package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.application.type.CharacterSpecificsMap;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactAttuneType;
import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.library.initialization.ObjectFactory;

public class ReflectionMaterialRules implements MaterialRules {
  private final CharacterSpecificsMap<CharacterTypeMaterialRules> map;

  public ReflectionMaterialRules(ObjectFactory objectFactory) {
    this.map = new CharacterSpecificsMap<>(objectFactory, CharacterTypeMaterialRules.class, new DefaultMaterialRules());
  }

  public MagicalMaterial getDefault(CharacterType characterType) {
    CharacterTypeMaterialRules rules = map.getForCharacterType(characterType);
    return rules.getDefault();
  }

  public ArtifactAttuneType[] getAttunementTypes(CharacterType characterType,
                                                 MagicalMaterial material) {
    CharacterTypeMaterialRules rules = map.getForCharacterType(characterType);
    return rules.getAttunementTypes(material);
  }

  public boolean canAttuneToMalfeanMaterials(CharacterType characterType) {
    CharacterTypeMaterialRules rules = map.getForCharacterType(characterType);
    return rules.canAttuneToMalfeanMaterials();
  }
}