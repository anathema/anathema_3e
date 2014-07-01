package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.CharmData;
import net.sf.anathema.charm.data.reference.MagicName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.charm.old.cost.CostList;
import net.sf.anathema.charm.old.source.SourceBook;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.duration.Duration;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;
import java.util.Set;

public class DefaultCharm implements Charm {

  private CharmData charmData;

  public DefaultCharm(CharmData charmData) {
    this.charmData = charmData;
  }

  @Override
  public TreeReference getTreeReference() {
    return charmData.getTreeReference();
  }

  @Override
  public MagicName getMagicName() {
    return charmData.getMagicName();
  }

  @Override
  public Duration getDuration() {
    return null;
  }

  @Override
  public ValuedTraitType getEssence() {
    return null;
  }

  @Override
  public ValuedTraitType[] getPrerequisites() {
    return new ValuedTraitType[0];
  }

  @Override
  public TraitType getPrimaryTraitType() {
    return null;
  }

  @Override
  public List<CharmLearnPrerequisite> getLearnPrerequisites() {
    return null;
  }

  @Override
  public CharmType getCharmType() {
    return null;
  }

  @Override
  public Set<Charm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator) {
    return null;
  }

  @Override
  public Set<Charm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator) {
    return null;
  }

  @Override
  public <T extends CharmLearnPrerequisite> List<T> getPrerequisitesOfType(Class<T> clazz) {
    return null;
  }

  @Override
  public boolean isBlockedByAlternative(ICharmLearnArbitrator learnArbitrator) {
    return false;
  }

  @Override
  public Set<Charm> getLearnChildCharms() {
    return null;
  }

  @Override
  public Set<Charm> getMergedCharms() {
    return null;
  }

  @Override
  public boolean isTreeRoot() {
    return false;
  }

  @Override
  public Set<Charm> getRenderingPrerequisiteCharms() {
    return null;
  }

  @Override
  public MagicAttribute[] getAttributes() {
    return new MagicAttribute[0];
  }

  @Override
  public SourceBook[] getSources() {
    return new SourceBook[0];
  }

  @Override
  public SourceBook getPrimarySource() {
    return null;
  }

  @Override
  public CostList getTemporaryCost() {
    return null;
  }

  @Override
  public boolean isFavored(Hero hero) {
    return false;
  }

  @Override
  public boolean hasAttribute(Identifier attribute) {
    return false;
  }

  @Override
  public String getAttributeValue(Identifier attribute) {
    return null;
  }
}
