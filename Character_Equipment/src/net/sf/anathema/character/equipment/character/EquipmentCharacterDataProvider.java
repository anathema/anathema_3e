package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.CharacterMaterialRules;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.control.IChangeListener;

import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.*;

public class EquipmentCharacterDataProvider implements IEquipmentCharacterDataProvider {

  private final ICharacterModelContext context;

  public EquipmentCharacterDataProvider(ICharacterModelContext context) {
    this.context = context;
  }

  @Override
  public INamedGenericTrait[] getSpecialties(ITraitType trait) {
    return context.getSpecialtyContext().getSpecialties(trait);
  }

  @Override
  public IEquipmentStatsOption getCharacterSpecialtyOption(String name, String type) {
    ITraitType trait = AbilityType.valueOf(type);
    for (INamedGenericTrait specialty : context.getSpecialtyContext().getSpecialties(trait))
      if (specialty.getName().equals(name)) return new EquipmentSpecialtyOption(specialty, trait);
    return null;
  }

  @Override
  public ArtifactAttuneType[] getAttuneTypes(IEquipmentItem item) {
    MagicalMaterial material = item.getMaterial();
    switch (item.getMaterialComposition()) {
      default:
      case None:
        return new ArtifactAttuneType[0];
      case Fixed:
      case Variable:
        return new CharacterMaterialRules().getAttunementTypes(getCharacterType(), material);
      case Compound:
        return new ArtifactAttuneType[]{Unattuned, ArtifactAttuneType.FullyAttuned};
      case MalfeanMaterials:
        return createMalfeanMaterialsAttunementOptions();
    }
  }

  private ArtifactAttuneType[] createMalfeanMaterialsAttunementOptions() {
    if (new CharacterMaterialRules().canAttuneToMalfeanMaterials(getCharacterType())) {
      return new ArtifactAttuneType[]{Unattuned, FullyAttuned};
    }
    return new ArtifactAttuneType[]{Unattuned, UnharmoniouslyAttuned};
  }

  @Override
  public void addCharacterSpecialtyListChangeListener(IChangeListener listener) {
    context.getSpecialtyContext().addSpecialtyListChangeListener(listener);
  }

  private ICharacterType getCharacterType() {
    return context.getBasicCharacterContext().getCharacterType();
  }
}