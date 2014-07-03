package net.sf.anathema.charm.data.prerequisite;

import net.sf.anathema.magic.data.attribute.MagicAttribute;
import net.sf.anathema.charm.data.Charm;

public interface PrerequisiteProcessor {

  void requiresMagicAttributes(MagicAttribute attribute, int count);

  void requiresCharm(Charm prerequisite);

  void requiresCharmFromSelection(Charm[] prerequisites, int threshold);
}
