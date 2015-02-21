package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.charm.data.prerequisite.AnyOneTraitCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.AttributeKnownCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.EvocationTierPrerequisite;
import net.sf.anathema.charm.data.prerequisite.PrerequisiteVisitor;
import net.sf.anathema.charm.data.prerequisite.SimpleCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.SpecificGroupCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.TraitGroupCharmPrerequisite;
import net.sf.anathema.hero.charms.display.view.NodeIds;

public class NonSpecificNodeIdVisitor implements PrerequisiteVisitor {
  public String nodeId;


  @Override
  public void visit(AnyOneTraitCharmPrerequisite prerequisite) {
    nodeId = NodeIds.getNodeId(prerequisite);
  }

  @Override
  public void visit(AttributeKnownCharmPrerequisite prerequisite) {
    nodeId = NodeIds.getNodeId(prerequisite);
  }

  @Override
  public void visit(SimpleCharmPrerequisite prerequisite) {
    throw new UnsupportedOperationException("Applied to concrete prerequisite");
  }

  @Override
  public void visit(SpecificGroupCharmPrerequisite prerequisite) {
    throw new UnsupportedOperationException("Applied to concrete prerequisite");
  }

  @Override
  public void visit(TraitGroupCharmPrerequisite prerequisite) {
    nodeId = NodeIds.getNodeId(prerequisite);
  }

  @Override
  public void visit(EvocationTierPrerequisite prerequisite) {
    nodeId = NodeIds.getNodeId(prerequisite);
  }
}