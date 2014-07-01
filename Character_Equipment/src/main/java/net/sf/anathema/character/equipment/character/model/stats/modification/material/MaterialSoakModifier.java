package net.sf.anathema.character.equipment.character.model.stats.modification.material;

import net.sf.anathema.character.equipment.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.character.model.stats.modification.StatModifier;

public class MaterialSoakModifier implements StatModifier {
  private final BaseMaterial material;

  public MaterialSoakModifier(BaseMaterial material) {
    this.material = material;
  }

  @Override
  public int calculate() {
    if (material.isOrichalcumBased() || material.isSoulsteelBased()) {
      return 2;
    }
    if (material.isAdamantBased()) {
      return 3;
    }
    return 0;
  }
}