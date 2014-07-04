package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactAttuneType;
import net.sf.anathema.hero.individual.splat.CharacterType;

public interface MaterialRules {
  MagicalMaterial getDefault(CharacterType characterType);

  ArtifactAttuneType[] getAttunementTypes(CharacterType characterType,
                                          MagicalMaterial material);

  boolean canAttuneToMalfeanMaterials(CharacterType characterType);
}