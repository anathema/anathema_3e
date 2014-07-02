package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.hero.charms.display.presenter.CharmGroupInformer;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.platform.tree.display.TreeView;

import java.util.ArrayList;
import java.util.List;

public class ConfigurableCharmDye implements CharmDye {

  private final CharmGroupInformer groupInformer;
  private final CharmColoring coloring;
  private final List<CharmBrush> brushes = new ArrayList<>();

  public ConfigurableCharmDye(CharmGroupInformer informer, CharmColoring coloring) {
    this.groupInformer = informer;
    this.coloring = coloring;
    brushes.add(new SimpleCharmBrush(coloring));
    brushes.add(new ExternalPrerequisitesBrush(groupInformer, coloring));
    brushes.add(new NonCharmPrerequisitesBrush(coloring));
  }

  public void colorCharm(Charm charm) {
    coloring.colorCharm(charm);
  }

  public void setCharmVisuals() {
    if (!groupInformer.hasGroupSelected()) {
      return;
    }
    for (Charm charm : getAllCharmsFromCurrentGroup()) {
      for (CharmBrush brush : brushes) {
        brush.color(charm);
      }
    }
  }

  @Override
  public void operateOn(TreeView treeView) {
    coloring.operateOn(treeView);
  }

  private Charm[] getAllCharmsFromCurrentGroup() {
    return groupInformer.getCurrentTree().getAllCharms();
  }
}