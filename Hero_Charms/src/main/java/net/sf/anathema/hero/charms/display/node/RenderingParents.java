package net.sf.anathema.hero.charms.display.node;

import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.prerequisite.PrerequisiteProcessor;

import java.util.ArrayList;
import java.util.List;

public class RenderingParents  implements PrerequisiteProcessor {

  public static List<Charm> collectRenderingParents(Charm charm) {
    RenderingParents renderingParents = new RenderingParents();
    charm.forEachCharmPrerequisite(charmPrerequisite -> charmPrerequisite.process(renderingParents));
    return renderingParents.renderingParents;
  }

  public List<Charm> renderingParents = new ArrayList<>();

  @Override
  public void requiresMagicAttributes(MagicAttribute attribute, int count) {
    // nothing to do
  }

  @Override
  public void requiresCharm(Charm prerequisite) {
    renderingParents.add(prerequisite);
  }

  @Override
  public void requiresCharmFromSelection(Charm[] prerequisites, int threshold) {
    for (Charm prerequisite : prerequisites) {
      renderingParents.add(prerequisite);
    }
  }
}