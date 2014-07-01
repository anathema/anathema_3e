package net.sf.anathema.charm.data.reference;

public class TreeReference {
  public final TreeCategoryReference category;
  public final TreeName name;

  public TreeReference(TreeCategoryReference category, TreeName name) {
    this.category = category;
    this.name = name;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TreeReference)) {
      return false;
    }
    TreeReference other = (TreeReference) obj;
    return other.name.equals(name) && other.category.equals(category);
  }

  @Override
  public int hashCode() {
    return name.hashCode() * 3 + category.hashCode() * 7;
  }

  @Override
  public String toString() {
    return category + ", " + name;
  }
}
