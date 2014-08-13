package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.charm.data.prerequisite.AnyOneTraitCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.AttributeKnownCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.DirectGroupCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.PrerequisiteVisitor;
import net.sf.anathema.charm.data.prerequisite.SimpleCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.SpecificGroupCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.TraitGroupCharmPrerequisite;
import static net.sf.anathema.hero.charms.display.view.NodeIds.getNodeId;

public class ColorNonCharmPrerequisites implements PrerequisiteVisitor {
  private CharmColoring coloring;

  public ColorNonCharmPrerequisites(CharmColoring coloring) {
    this.coloring = coloring;
  }

  @Override
  public void visit(AttributeKnownCharmPrerequisite prerequisite) {
    String nodeId = getNodeId(prerequisite);
    coloring.colorNonCharmPrerequisite(nodeId, prerequisite);
  }

  @Override
  public void visit(DirectGroupCharmPrerequisite prerequisite) {
    // nothing to do
  }

  @Override
  public void visit(SimpleCharmPrerequisite prerequisite) {
    // nothing to do
  }

  @Override
  public void visit(TraitGroupCharmPrerequisite prerequisite) {
	  String nodeId = getNodeId(prerequisite);
	  coloring.colorNonCharmPrerequisite(nodeId, prerequisite);
  }
  
  @Override
  public void visit(AnyOneTraitCharmPrerequisite prerequisite) {
	  String nodeId = getNodeId(prerequisite);
	  coloring.colorNonCharmPrerequisite(nodeId, prerequisite);
  }

	@Override
	public void visit(SpecificGroupCharmPrerequisite prerequisite) {
		// nothing to do
	}
  
  
}
