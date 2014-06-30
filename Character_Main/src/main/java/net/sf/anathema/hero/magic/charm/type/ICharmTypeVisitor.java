package net.sf.anathema.hero.magic.charm.type;

public interface ICharmTypeVisitor {

  void visitSimple(CharmType visitedType);

  void visitExtraAction(CharmType visitedType);

  void visitReflexive(CharmType visitedType);

  void visitSupplemental(CharmType visitedType);

  void visitPermanent(CharmType visitedType);

  void visitSpecial(CharmType visitedType);
}