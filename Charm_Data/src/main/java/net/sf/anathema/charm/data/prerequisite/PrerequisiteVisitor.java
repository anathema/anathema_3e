package net.sf.anathema.charm.data.prerequisite;

public interface PrerequisiteVisitor {

  void visit(AttributeKnownCharmPrerequisite prerequisite);

  void visit(DirectGroupCharmPrerequisite prerequisite);

  void visit(SimpleCharmPrerequisite prerequisite);
}
