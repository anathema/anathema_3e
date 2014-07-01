package net.sf.anathema.hero.magic.basic;

import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMagic implements Magic {
  private final List<MagicAttribute> magicAttributes = new ArrayList<>();
  private final String id;

  protected AbstractMagic(String id) {
    this.id = id;
  }

  @Override
  public final String getId() {
    return id;
  }

  public void addMagicAttribute(MagicAttribute attribute) {
    magicAttributes.add(attribute);
  }

  @Override
  public MagicAttribute[] getAttributes() {
    return magicAttributes.toArray(new MagicAttribute[magicAttributes.size()]);
  }

  @Override
  public boolean hasAttribute(Identifier attribute) {
    return magicAttributes.contains(attribute);
  }

  @Override
  public String getAttributeValue(Identifier attribute) {
    int index = magicAttributes.indexOf(attribute);
    if (index < 0) {
      return null;
    }
    return magicAttributes.get(index).getValue();
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof AbstractMagic)) {
      return false;
    }
    AbstractMagic other = (AbstractMagic) obj;
    return other.id.equals(id);
  }

  @Override
  public int hashCode() {
    return getId().hashCode();
  }

  @Override
  public String toString() {
    return id;
  }
}
