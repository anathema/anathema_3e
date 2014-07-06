package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmAttributeList;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.library.identifier.Identifier;

import java.util.ArrayList;
import java.util.List;

public class CharmTreeImpl implements CharmTree, Identifier {

  private final Charm[] charms;
  private TreeReference reference;

  public CharmTreeImpl(TreeReference reference, Charm[] charms) {
    this.reference = reference;
    this.charms = charms;
  }

  @Override
  public final String getId() {
    return reference.name.text;
  }

  @Override
  public String toString() {
    return getId();
  }

  @Override
  public Charm[] getAllCharms() {
    return charms;
  }

  @Override
  public TreeReference getReference() {
    return reference;
  }

  @Override
  public boolean isCharmFromTree(Charm charm) {
    TreeReference referenceOfCharm = charm.getTreeReference();
    return referenceOfCharm.equals(reference);
  }

  @Override
  public Charm[] getCoreCharms() {
    Charm[] allCharms = getAllCharms();
    List<Charm> charms = new ArrayList<>();
    for (Charm charm : allCharms) {
      if (!charm.hasAttribute(CharmAttributeList.EXCLUSIVE_ATTRIBUTE)) {
        charms.add(charm);
      }
    }
    return charms.toArray(new Charm[charms.size()]);
  }
}