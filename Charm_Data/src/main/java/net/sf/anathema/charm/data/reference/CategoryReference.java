package net.sf.anathema.charm.data.reference;

import net.sf.anathema.lib.util.Identifier;

public class CategoryReference implements Identifier {

  public final String text;

  public CategoryReference(String id) {
    this.text = id;
  }

  @Override
  public String getId() {
    return text;
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
