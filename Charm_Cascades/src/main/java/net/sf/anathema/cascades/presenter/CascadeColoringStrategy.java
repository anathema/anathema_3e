package net.sf.anathema.cascades.presenter;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.coloring.CharmColoring;
import net.sf.anathema.platform.tree.display.TreeView;

public class CascadeColoringStrategy implements CharmColoring {
  private TreeView treeView;

  @Override
  public void colorCharm(Charm charm) {
    treeView.colorNode(charm.getName().text, RGBColor.White);
  }

  @Override
  public void colorNonCharmPrerequisite(String nodeId, CharmPrerequisite prerequisite) {
    treeView.colorNode(nodeId, RGBColor.White);
  }

  @Override
  public void operateOn(TreeView treeView) {
    this.treeView = treeView;
  }
}
