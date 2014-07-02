package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.old.attribute.MagicAttribute;

public interface CharmPrerequisiteVisitor {

  void requiresMagicAttributes(MagicAttribute attribute, int count);

  void requiresCharm(CharmName prerequisiteName);

  void requiresCharmFromSelection(CharmName[] prerequisiteIds, int threshold);
}
