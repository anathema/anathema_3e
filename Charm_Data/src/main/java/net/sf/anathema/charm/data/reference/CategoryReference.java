package net.sf.anathema.charm.data.reference;

public class CategoryReference {

  public final String text;

  public CategoryReference(String charmName) {
    this.text = charmName;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CategoryReference)) {
      return false;
    }
    CategoryReference other = (CategoryReference) obj;
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
