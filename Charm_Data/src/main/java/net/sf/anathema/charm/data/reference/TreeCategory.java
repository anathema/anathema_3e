package net.sf.anathema.charm.data.reference;

public class TreeCategory {

  public final String text;

  public TreeCategory(String charmName) {
    this.text = charmName;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TreeCategory)) {
      return false;
    }
    TreeCategory other = (TreeCategory) obj;
    return other.text.equals(text);
  }

  @Override
  public int hashCode() {
    return text.hashCode();
  }

  @Override
  public String toString() {
    return "Category: " + text;
  }
}
