package net.sf.anathema.hero.charms.dummy;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.CharmType;
import net.sf.anathema.magic.data.Duration;
import net.sf.anathema.magic.data.PrerequisiteList;
import net.sf.anathema.magic.data.cost.CostListImpl;
import net.sf.anathema.magic.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.magic.data.prerequisite.RequiredTraitType;
import net.sf.anathema.magic.data.prerequisite.SimpleCharmPrerequisite;
import net.sf.anathema.magic.data.prerequisite.TraitPrerequisite;
import net.sf.anathema.magic.data.reference.CategoryReference;
import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.magic.data.reference.TreeName;
import net.sf.anathema.magic.data.reference.TreeReference;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.magic.data.attribute.MagicAttribute;
import net.sf.anathema.magic.data.source.SourceBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DummyCharm extends SimpleIdentifier implements Charm, PrerequisiteList {

  public static DummyCharm ForIdAndTree(String id, String tree) {
    DummyCharm charm = new DummyCharm(id);
    charm.setTreeName(tree);
    return charm;
  }

  private Duration duration;
  public List<TraitPrerequisite> traitPrerequisites = new ArrayList<>();
  public List<CharmPrerequisite> charmPrerequisites = new ArrayList<>();
  public List<MagicAttribute> attributes = new ArrayList<>();
  private CharmType charmType;
  public TreeReference treeReference = new TreeReference(new CategoryReference("AnyCategory"), new TreeName("AnyTree"));

  public DummyCharm() {
    this(null);
  }

  public DummyCharm(String id, Charm[] parents, TraitPrerequisite ... prerequisites) {
    this(id, parents);
    this.traitPrerequisites = Arrays.asList(prerequisites);
  }

  public DummyCharm(String duration, CharmType charmType, TraitPrerequisite ... prerequisites) {
    this("DummyCharmDefaultId");
    this.duration = new Duration(duration);
    this.charmType = charmType;
    this.traitPrerequisites = Arrays.asList(prerequisites);
  }

  public DummyCharm(String id, Charm... parents) {
    super(id);
    for (Charm charm : parents) {
      charmPrerequisites.add(new SimpleCharmPrerequisite(charm));
    }
  }

  @Override
  public TreeReference getTreeReference() {
    return treeReference;
  }

  @Override
  public CharmName getName() {
    return getId() != null ? new CharmName(getId()) : null;
  }

  @Override
  public Duration getDuration() {
    return duration;
  }

  @Override
  public RequiredTraitType getPrimaryTraitType() {
    return traitPrerequisites.get(0).type;
  }

  @Override
  public void forEachTraitPrerequisite(Consumer<TraitPrerequisite> consumer) {
    traitPrerequisites.forEach(consumer);
  }

  @Override
  public void forEachChild(Consumer<Charm> consumer) {
    // nothing to do
  }

  @Override
  public void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer) {
    charmPrerequisites.forEach(consumer);
  }

  @Override
  public boolean hasCharmPrerequisites() {
    return !charmPrerequisites.isEmpty();
  }

  @Override
  public PrerequisiteList getPrerequisites() {
    return this;
  }

  @Override
  public CostListImpl getTemporaryCost() {
    return null;
  }

  @Override
  public boolean hasAttribute(Identifier attribute) {
    for (MagicAttribute magicAttribute : attributes) {
      if (magicAttribute.getId().equals(attribute.getId())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return getId();
  }

  public void setTreeName(String treeName) {
    this.treeReference = new TreeReference(this.treeReference.category, new TreeName(treeName));
  }

  @Override
  public List<SourceBook> getSources() {
    return Collections.emptyList();
  }

  @Override
  public CharmType getCharmType() {
    return charmType;
  }

  @Override
  public Iterable<MagicAttribute> getAttributes() {
    return attributes;
  }
}