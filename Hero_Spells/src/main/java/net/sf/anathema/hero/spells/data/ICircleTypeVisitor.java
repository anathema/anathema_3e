package net.sf.anathema.hero.spells.data;

public interface ICircleTypeVisitor {

  void visitTerrestrial(CircleType type);

  void visitCelestial(CircleType type);

  void visitSolar(CircleType type);
}