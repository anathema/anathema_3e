package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.prerequisite.IndirectCharmPrerequisite;

public class NonCharmPrerequisitesBrush implements CharmBrush {
  private CharmColoring coloring;

  public NonCharmPrerequisitesBrush(CharmColoring coloring) {
    this.coloring = coloring;
  }

  public void color(Charm charm) {
    for (IndirectCharmPrerequisite prerequisite : charm.getPrerequisitesOfType(IndirectCharmPrerequisite.class)) {
      coloring.colorNonCharmPrerequisite(prerequisite);
    }
  }
}