package net.sf.anathema.hero.charms.display.prerequisites;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.prerequisite.PrerequisiteProcessorAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConcreteCharmRenderingParents extends PrerequisiteProcessorAdapter {

  public static List<Charm> collectRenderingParents(Charm charm) {
    ConcreteCharmRenderingParents concreteCharmRenderingParents = new ConcreteCharmRenderingParents();
    charm.getPrerequisites().forEachCharmPrerequisite(prerequisite -> prerequisite.process(concreteCharmRenderingParents));
    return concreteCharmRenderingParents.renderingParents;
  }

  private final List<Charm> renderingParents = new ArrayList<>();

  @Override
  public void requiresCharm(Charm prerequisite) {
    renderingParents.add(prerequisite);
  }

  @Override
  public void requiresCharmFromSelection(Charm[] prerequisites, int count) {
    Collections.addAll(renderingParents, prerequisites);
  }
}