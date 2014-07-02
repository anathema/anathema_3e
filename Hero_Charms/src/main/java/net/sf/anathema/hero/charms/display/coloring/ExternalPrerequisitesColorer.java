package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.hero.charms.display.presenter.CharmGroupInformer;
import net.sf.anathema.hero.magic.charm.Charm;

import static net.sf.anathema.hero.charms.display.node.RenderingParents.collectRenderingParents;

public class ExternalPrerequisitesColorer implements CharmColorer {
  private final CharmGroupInformer groupInformer;
  private final CharmColoring coloring;

  public ExternalPrerequisitesColorer(CharmGroupInformer groupInformer, CharmColoring coloring) {
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