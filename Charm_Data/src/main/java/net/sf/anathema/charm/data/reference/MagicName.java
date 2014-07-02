package net.sf.anathema.charm.data.reference;

import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class MagicName extends ReflectionEqualsObject{

  public final String text;

  public MagicName(String charmName) {
    this.text = charmName;
  }

  @Override
  public String toString() {
    return text;
  }
}
