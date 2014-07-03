package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.display.presenter.CharmGroupInformer;

import static net.sf.anathema.hero.charms.display.prerequisites.ConcreteCharmRenderingParents.collectRenderingParents;

public class ExternalPrerequisitesBrush implements CharmBrush {
  private final CharmGroupInformer groupInformer;
  private final CharmColoring coloring;

  public ExternalPrerequisitesBrush(CharmGroupInformer groupInformer, CharmColoring coloring) {
    this.groupInformer = groupInformer;
    this.coloring = coloring;
  }

  public void color(Charm charm) {
    for (Charm prerequisite : collectRenderingParents(charm)) {
      if (isPartOfCurrentGroup(prerequisite)) {
        return;
      }
      coloring.colorCharm(prerequisite);

    }
  }

  private boolean isPartOfCurrentGroup(Charm charm) {
    return groupInformer.getCurrentTree().isCharmFromTree(charm);
  }
}