package net.sf.anathema.hero.magic.basic;

import net.sf.anathema.charm.data.reference.MagicName;
import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMagic implements Magic {
  private final List<MagicAttribute> magicAttributes = new ArrayList<>();
  private MagicName magicName;

  protected AbstractMagic(MagicName magicName) {
    this.magicName = magicName;
  }

  @Override
  public MagicName getMagicName() {
    return magicName;
  }

  @Override
  public String getId() {
    return magicName.text;
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
    return other.getId().equals(getId());
  }

  @Override
  public int hashCode() {
    return magicName.hashCode();
  }

  @Override
  public String toString() {
    return magicName.toString();
  }
}
