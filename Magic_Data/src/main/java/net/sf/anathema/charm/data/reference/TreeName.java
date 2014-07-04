package net.sf.anathema.charm.data.reference;

public class TreeName {

  public final String text;

  public TreeName(String charmName) {
    this.text = charmName;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TreeName)) {
      return false;
    }
    TreeName other = (TreeName) obj;
    return other.text.equals(text);
  }

  @Override
  public int hashCode() {
    return text.hashCode();
  }

  @Override
  public String toString() {
    return "Tree: " + text;
  }
}
