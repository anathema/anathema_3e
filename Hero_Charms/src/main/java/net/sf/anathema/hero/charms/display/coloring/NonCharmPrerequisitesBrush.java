package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.display.prerequisites.NonCharmPrerequisiteId;

public class NonCharmPrerequisitesBrush implements CharmBrush {
  private CharmColoring coloring;

  public NonCharmPrerequisitesBrush(CharmColoring coloring) {
    this.coloring = coloring;
  }

  public void color(Charm charm) {
    charm.getPrerequisites().forEachCharmPrerequisite(prerequisite -> {
      if (prerequisite.isSpecific()){
        return;
      }
      NonCharmPrerequisiteId processor = new NonCharmPrerequisiteId();
      prerequisite.process(processor);
      coloring.colorNonCharmPrerequisite(processor.id, prerequisite);
    });
  }
}