package net.sf.anathema.charm.data.prerequisite;

public interface PrerequisiteVisitor {
	
	void visit(AnyOneTraitCharmPrerequisite prerequisite);

  void visit(AttributeKnownCharmPrerequisite prerequisite);

  void visit(DirectGroupCharmPrerequisite prerequisite);

  void visit(SimpleCharmPrerequisite prerequisite);
  
  void visit(SpecificGroupCharmPrerequisite prerequisite);
  
  void visit(TraitGroupCharmPrerequisite prerequisite);
}
