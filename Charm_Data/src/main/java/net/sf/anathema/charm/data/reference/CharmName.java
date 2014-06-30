package net.sf.anathema.charm.data.reference;

public class CharmName {

  public final String text;

  public CharmName(String charmName) {
    this.text = charmName;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CharmName)) {
      return false;
    }
    CharmName other = (CharmName) obj;
    return other.text.equals(text);
  }

  @Override
  public int hashCode() {
    return text.hashCode();
  }
}
