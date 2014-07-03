package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.magic.attribute.MagicAttribute;
import net.sf.anathema.hero.magic.charm.UnlinkedCharmMap;

public class AttributeKnownCharmPrerequisite implements CharmPrerequisite{

  private final MagicAttribute attribute;
  private final int count;

  public AttributeKnownCharmPrerequisite(MagicAttribute attribute, int count) {
    this.attribute = attribute;
    this.count = count;
  }

  @Override
  public void process(PrerequisiteProcessor processor) {
    processor.requiresMagicAttributes(attribute, count);
  }

  @Override
  public void accept(PrerequisiteVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void link(UnlinkedCharmMap charmsById) {
    // nothing to do
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof AttributeKnownCharmPrerequisite) {
      AttributeKnownCharmPrerequisite prerequisite = (AttributeKnownCharmPrerequisite) obj;
      return prerequisite.attribute.equals(attribute) && prerequisite.count == count;
    }
    return false;
  }
}
