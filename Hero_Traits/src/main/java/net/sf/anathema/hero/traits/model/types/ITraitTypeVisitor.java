package net.sf.anathema.hero.traits.model.types;

public interface ITraitTypeVisitor {

  void visitAbility(AbilityType type);

  void visitAttribute(AttributeType type);

  void visitEssence(OtherTraitType type);

  void visitWillpower(OtherTraitType type);
}