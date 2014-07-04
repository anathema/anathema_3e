package net.sf.anathema.charm.data.prerequisite;

public interface CharmPrerequisite {

  void process(PrerequisiteProcessor processor);

  void accept(PrerequisiteVisitor visitor);
}
