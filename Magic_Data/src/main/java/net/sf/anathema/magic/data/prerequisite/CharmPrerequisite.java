package net.sf.anathema.magic.data.prerequisite;

public interface CharmPrerequisite {

  void process(PrerequisiteProcessor processor);

  void accept(PrerequisiteVisitor visitor);
  
  boolean isSpecific();
}
