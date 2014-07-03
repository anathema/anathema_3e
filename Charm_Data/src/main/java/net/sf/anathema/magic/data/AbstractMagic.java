package net.sf.anathema.magic.data;

import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMagic implements Magic {
  private final List<MagicAttribute> magicAttributes = new ArrayList<>();

  public void addMagicAttribute(MagicAttribute attribute) {
    magicAttributes.add(attribute);
  }

  @Override
  public MagicAttribute[] getAttributes() {
    return magicAttributes.toArray(new MagicAttribute[magicAttributes.size()]);
  }

  @Override
  public boolean hasAttribute(Identifier attribute) {
    return magicAttributes.stream().anyMatch(identifier -> identifier.getId().equals(attribute.getId()));
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof AbstractMagic)) {
      return false;
    }
    AbstractMagic other = (AbstractMagic) obj;
    return other.getName().text.equals(getName().text);
  }

  @Override
  public int hashCode() {
    return getName().hashCode();
  }

  @Override
  public String toString() {
    return getName().toString();
  }
}
