package net.sf.anathema.cascades.presenter;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.charms.display.coloring.CharmBorderColorEvaluator;
import net.sf.anathema.hero.charms.display.coloring.CharmColoring;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.presenter.RGBColor;
import net.sf.anathema.platform.tree.display.TreeView;

public class CascadeColoringStrategy implements CharmColoring {
  private TreeView treeView;
  private CharmBorderColorEvaluator evaluator;
  
  public CascadeColoringStrategy(CharmBorderColorEvaluator evaluator) {
  	this.evaluator = evaluator;
  }

  @Override
  public void colorCharm(Charm charm) {
    treeView.colorNode(charm.getName().text, RGBColor.White, evaluator.getBorderColorForCharm(charm));
  }

  @Override
  public void colorNonCharmPrerequisite(String nodeId, CharmPrerequisite prerequisite) {
    treeView.colorNode(nodeId, RGBColor.White, RGBColor.Black);
  }

  @Override
  public void operateOn(TreeView treeView) {
    this.treeView = treeView;
  }
}
