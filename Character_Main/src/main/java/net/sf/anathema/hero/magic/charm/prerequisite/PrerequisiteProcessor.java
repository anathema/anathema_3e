package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.magic.attribute.MagicAttribute;
import net.sf.anathema.hero.magic.charm.Charm;

public interface PrerequisiteProcessor {

  void requiresMagicAttributes(MagicAttribute attribute, int count);

  void requiresCharm(Charm prerequisite);

  void requiresCharmFromSelection(Charm[] prerequisites, int threshold);
}
