package net.sf.anathema.character.generic.template.magic;

public interface IFavoringTraitTypeVisitor {

  void visitAbilityType(FavoringTraitType visitedType);

  void visitAttributeType(FavoringTraitType visitedType);
}