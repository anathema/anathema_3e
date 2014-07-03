package net.sf.anathema.hero.dummy;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.data.cost.CostListImpl;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.charm.CharmParent;
import net.sf.anathema.hero.magic.charm.PrerequisiteList;
import net.sf.anathema.hero.magic.charm.duration.Duration;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.SimpleCharmPrerequisite;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.magic.attribute.MagicAttribute;
import net.sf.anathema.magic.source.SourceBook;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DummyCharm extends SimpleIdentifier implements Charm, CharmParent, PrerequisiteList {

  public static DummyCharm ForIdAndTree(String id, String tree) {
    DummyCharm charm = new DummyCharm(id);
    charm.setTreeName(tree);
    return charm;
  }

  private Duration duration;
  private ValuedTraitType[] prerequisites;
  private List<CharmPrerequisite> learnPrerequisites = new ArrayList<>();
  public List<MagicAttribute> attributes = new ArrayList<>();
  private CharmType charmType;
  public TreeReference treeReference = new TreeReference(new CategoryReference("AnyCategory"), new TreeName("AnyTree"));

  public DummyCharm() {
    this(null);
  }

  public DummyCharm(String id) {
    this(id, new Charm[0]);
  }

  public DummyCharm(String id, Charm... parents) {
    this(id, parents, new ValuedTraitType[0]);
  }

  public DummyCharm(String id, Charm[] parents, ValuedTraitType[] prerequisites) {
    super(id);
    for (Charm charm : parents) {
      learnPrerequisites.add(new SimpleCharmPrerequisite(charm));
    }
    this.prerequisites = prerequisites;
  }

  public DummyCharm(String duration, CharmType charmType, ValuedTraitType[] prerequisites) {
    super("DummyCharmDefaultId");
    this.prerequisites = prerequisites;
    this.duration = new Duration(duration);
    this.charmType = charmType;
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
  public ValuedTraitType getEssence() {
    return null;
  }

  @Override
  public List<CharmPrerequisite> getCharmPrerequisites() {
    return learnPrerequisites;
  }

  @Override
  public ValuedTraitType[] getTraitPrerequisites() {
    return prerequisites;
  }

  @Override
  public TraitType getPrimaryTraitType() {
    return prerequisites[0].getType();
  }

  @Override
  public void forEachChild(Consumer<Charm> consumer) {
    // nothing to do
  }

  @Override
  public void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer) {
    learnPrerequisites.forEach(consumer);
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
  public SourceBook[] getSources() {
    return new SourceBook[]{null};
  }

  @Override
  public CharmType getCharmType() {
    return charmType;
  }

  @Override
  public MagicAttribute[] getAttributes() {
    return attributes.toArray(new MagicAttribute[attributes.size()]);
  }

  @Override
  public void addChild(CharmImpl child) {
    //nothing to do
  }
}