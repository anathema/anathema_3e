package net.sf.anathema.magic.data;

public interface CharmTypeVisitor {

  void visitSimple(CharmType visitedType);

  void visitSupplemental(CharmType visitedType);

  void visitReflexive(CharmType visitedType);

  void visitPermanent(CharmType visitedType);
}