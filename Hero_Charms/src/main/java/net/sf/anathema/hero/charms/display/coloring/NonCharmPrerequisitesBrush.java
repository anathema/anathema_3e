package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.hero.magic.charm.Charm;

public class NonCharmPrerequisitesBrush implements CharmBrush {
  private CharmColoring coloring;

  public NonCharmPrerequisitesBrush(CharmColoring coloring) {
    this.coloring = coloring;
  }

  public void color(Charm charm) {
    charm.getPrerequisites().forEachCharmPrerequisite(prerequisite -> {
      prerequisite.accept(new ColorNonCharmPrerequisites(coloring));
    });
  }

}