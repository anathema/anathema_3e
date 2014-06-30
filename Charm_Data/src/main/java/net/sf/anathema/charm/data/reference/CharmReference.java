package net.sf.anathema.charm.data.reference;

public class CharmReference {

  public TreeReference treeReference;
  public final CharmName charmName;

  public CharmReference(CharmName charmName, TreeReference treeReference) {
    this.treeReference = treeReference;
    this.charmName = charmName;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TreeReference)) {
      return false;
    }
    CharmReference other = (CharmReference) obj;
    return other.treeReference.equals(treeReference) && other.charmName.equals(charmName);
  }

  @Override
  public int hashCode() {
    return treeReference.hashCode() * 3 + charmName.hashCode() * 7;
  }
}
