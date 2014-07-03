package net.sf.anathema.hero.magic.charm.prerequisite;

public interface CharmPrerequisite {

  void process(PrerequisiteProcessor processor);

  void accept(PrerequisiteVisitor visitor);
}
