package net.sf.anathema.charm.data.reference;

public class TreeReference {
  public final CharacterTypeName typeName;
  public final TreeName treeName;

  public TreeReference(CharacterTypeName typeName, TreeName treeName) {
    this.typeName = typeName;
    this.treeName = treeName;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TreeReference)) {
      return false;
    }
    TreeReference other = (TreeReference) obj;
    return other.treeName.equals(treeName) && other.typeName.equals(treeName);
  }

  @Override
  public int hashCode() {
    return treeName.hashCode() * 3 + typeName.hashCode() * 7;
  }
}
