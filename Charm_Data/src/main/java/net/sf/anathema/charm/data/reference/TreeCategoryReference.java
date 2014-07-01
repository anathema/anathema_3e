package net.sf.anathema.charm.data.reference;

public class TreeCategoryReference {

  public final String text;

  public TreeCategoryReference(String charmName) {
    this.text = charmName;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TreeCategoryReference)) {
      return false;
    }
    TreeCategoryReference other = (TreeCategoryReference) obj;
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
