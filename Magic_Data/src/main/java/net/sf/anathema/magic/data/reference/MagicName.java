package net.sf.anathema.magic.data.reference;

import net.sf.anathema.library.lang.ReflectionEqualsObject;

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
