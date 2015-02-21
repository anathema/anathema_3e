package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.AnyOneTraitCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.AttributeKnownCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.EvocationTierPrerequisite;
import net.sf.anathema.charm.data.prerequisite.PrerequisiteVisitor;
import net.sf.anathema.charm.data.prerequisite.SimpleCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.SpecificGroupCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.TraitGroupCharmPrerequisite;

public class ExcludeSpecificPrerequisitesFromNonSpecificRequirements implements PrerequisiteVisitor {
  private Charm charm;

  public ExcludeSpecificPrerequisitesFromNonSpecificRequirements(Charm charm) {
    this.charm = charm;
  }

  @Override
  public void visit(AnyOneTraitCharmPrerequisite prerequisite) {
    prerequisite.excludeSpecificPrerequsitesOf(charm);
  }

  @Override
  public void visit(AttributeKnownCharmPrerequisite prerequisite) {
    prerequisite.excludeSpecificPrerequisitesOf(charm);
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
    prerequisite.excludeSpecificPrerequisitesOf(charm);
  }

  @Override
  public void visit(EvocationTierPrerequisite prerequisite) {
    //nothing to do
  }
}
