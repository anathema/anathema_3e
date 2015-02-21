package net.sf.anathema.charm.data.prerequisite;

public class PrerequisiteVisitorAdapter implements PrerequisiteVisitor {
  @Override
  public void visit(AnyOneTraitCharmPrerequisite prerequisite) {
    //nothing to do
  }

  @Override
  public void visit(AttributeKnownCharmPrerequisite prerequisite) {
    //nothing to do
  }

  @Override
  public void visit(DirectGroupCharmPrerequisite prerequisite) {
    //nothing to do
  }

  @Override
  public void visit(SimpleCharmPrerequisite prerequisite) {
    //nothing to do
  }

  @Override
  public void visit(SpecificGroupCharmPrerequisite prerequisite) {
    //nothing to do
  }

  @Override
  public void visit(TraitGroupCharmPrerequisite prerequisite) {
    //nothing to do
  }

  @Override
  public void visit(EvocationTierPrerequisite prerequisite) {
    //nothing to do
  }
}
