package net.sf.anathema.hero.magic.charm.prerequisite;

public interface PrerequisiteVisitor {

  void visit(AttributeKnownCharmPrerequisite prerequisite);

  void visit(DirectGroupCharmPrerequisite prerequisite);

  void visit(SimpleCharmPrerequisite prerequisite);
}
