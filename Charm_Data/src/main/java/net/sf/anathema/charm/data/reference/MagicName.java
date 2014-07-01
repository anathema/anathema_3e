package net.sf.anathema.charm.data.reference;

public class MagicName implements Comparable<MagicName>{

  public final String text;

  public MagicName(String charmName) {
    this.text = charmName;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof MagicName)) {
      return false;
    }
    MagicName other = (MagicName) obj;
    return other.text.equals(text);
  }

  @Override
  public int hashCode() {
    return text.hashCode();
  }

  @Override
  public int compareTo(MagicName o) {
    return text.compareTo(o.text);
  }
}
