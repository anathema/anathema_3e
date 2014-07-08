package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.library.identifier.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static net.sf.anathema.charm.data.CharmAttributeList.EXCLUSIVE_ATTRIBUTE;

public class CharmTreeImpl implements CharmTree, Identifier {

  private final Collection<Charm> charms;
  private TreeReference reference;

  public CharmTreeImpl(TreeReference reference, Collection<Charm> charms) {
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
  public Collection<Charm> getAllCharms() {
    return new ArrayList<>(charms);
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
  public Collection<Charm> getCoreCharms() {
    Stream<Charm> allCharms = getAllCharms().stream();
    return allCharms.filter(charm -> !charm.hasAttribute(EXCLUSIVE_ATTRIBUTE)).collect(toList());
  }
}