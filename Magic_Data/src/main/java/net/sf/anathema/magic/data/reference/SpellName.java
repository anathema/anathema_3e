package net.sf.anathema.magic.data.reference;

public class SpellName extends MagicName implements Comparable<SpellName> {

  public SpellName(String charmName) {
    super(charmName);
  }

  @Override
  public int compareTo(SpellName o) {
    return text.compareTo(o.text);
  }
}
