package net.sf.anathema.hero.charms.display.prerequisites;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.PrerequisiteProcessor;
import net.sf.anathema.charm.data.prerequisite.PrerequisiteProcessorAdapter;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

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
  public void requiresCharmFromSelection(Charm[] prerequisites, int threshold) {
    Collections.addAll(renderingParents, prerequisites);
  }
}