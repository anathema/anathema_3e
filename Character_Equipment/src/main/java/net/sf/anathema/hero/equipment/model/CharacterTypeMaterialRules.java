package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactAttuneType;

public interface CharacterTypeMaterialRules {
  MagicalMaterial getDefault();

  ArtifactAttuneType[] getAttunementTypes(MagicalMaterial material);

  boolean canAttuneToMalfeanMaterials();
}