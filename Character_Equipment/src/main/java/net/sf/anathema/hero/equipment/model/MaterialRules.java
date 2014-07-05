package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactAttuneType;
import net.sf.anathema.hero.individual.splat.HeroType;

public interface MaterialRules {
  MagicalMaterial getDefault(HeroType heroType);

  ArtifactAttuneType[] getAttunementTypes(HeroType heroType,
                                          MagicalMaterial material);

  boolean canAttuneToMalfeanMaterials(HeroType heroType);
}