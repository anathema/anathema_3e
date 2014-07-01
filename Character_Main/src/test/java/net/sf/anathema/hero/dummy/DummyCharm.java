package net.sf.anathema.hero.dummy;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.charm.old.cost.CostListImpl;
import net.sf.anathema.charm.old.source.SourceBook;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.charm.CharmParent;
import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.duration.Duration;
import net.sf.anathema.hero.magic.charm.duration.SimpleDuration;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.SimpleCharmLearnPrerequisite;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.*;

public class DummyCharm extends SimpleIdentifier implements Charm, CharmParent {

  private Duration duration;
  private ValuedTraitType[] prerequisites;
  private List<CharmLearnPrerequisite> learnPrerequisites = new ArrayList<>();
  private Set<Charm> parentCharms;
  private Set<Charm> learnFollowUpCharms = new HashSet<>();
  private CharacterType characterType;
  private String groupId;
  public List<MagicAttribute> attributes = new ArrayList<>();
  private CharmType charmType;

  public DummyCharm(String duration, CharmType charmType, ValuedTraitType[] prerequisites) {
    super("DummyCharmDefaultId");
    this.prerequisites = prerequisites;
    this.duration = SimpleDuration.getDuration(duration);
    this.charmType = charmType;
  }

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
    this.parentCharms = new LinkedHashSet<>();
    Collections.addAll(parentCharms, parents);
    for (Charm charm : parents) {
    	learnPrerequisites.add(new SimpleCharmLearnPrerequisite(charm));
    }
    this.prerequisites = prerequisites;
  }

  public void addLearnFollowUpCharm(Charm charm) {
    learnFollowUpCharms.add(charm);
  }

  @Override
  public TreeReference getTreeReference() {
    String category = MartialArtsUtilities.isMartialArts(this) ? MartialArtsUtilities.MARTIAL_ARTS.getId() : characterType.getId();
    return new TreeReference(new CategoryReference(category), new TreeName(groupId));
  }

  @Override
  public CharmName getCharmName() {
    return getId() != null ? new CharmName(getId()) : null;
  }

  @Override
  public CharacterType getCharacterType() {
    return characterType;
  }

  public void setCharacterType(CharacterType type) {
    characterType = type;
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
  public String getGroupId() {
    return groupId != null ? groupId : prerequisites[0].getType().getId();
  }

  @Override
  public Set<Charm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator) {
    return learnFollowUpCharms;
  }

  @Override
  public Set<Charm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator) {
    return parentCharms;
  }

  @Override
  public Set<Charm> getLearnChildCharms() {
    return learnFollowUpCharms;
  }
  
  @Override
  public List<CharmLearnPrerequisite> getLearnPrerequisites() {
  	return learnPrerequisites;
  }
  

  @Override
  public <T extends CharmLearnPrerequisite> List<T> getPrerequisitesOfType(Class<T> clazz) {
	  List<T> matches = new ArrayList<>();
	  for (CharmLearnPrerequisite prerequisite : learnPrerequisites) {
		  if (clazz.isInstance(prerequisite)) {
			  matches.add((T) prerequisite);
		  }
	  }
	  return matches;
  }

  @Override
  public ValuedTraitType[] getPrerequisites() {
    return prerequisites;
  }

  @Override
  public TraitType getPrimaryTraitType() {
    return prerequisites[0].getType();
  }

  @Override
  public Set<Charm> getRenderingPrerequisiteCharms() {
    return parentCharms;
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
  public String getAttributeValue(Identifier attribute) {
    return null;
  }

  @Override
  public boolean isBlockedByAlternative(ICharmLearnArbitrator learnArbitrator) {
    return false;
  }

  @Override
  public Set<Charm> getMergedCharms() {
    return new HashSet<>();
  }

  @Override
  public boolean isFavored(Hero hero) {
    if (prerequisites.length <= 0) {
      return false;
    }
    TraitMap traitMap = TraitModelFetcher.fetch(hero);
    ValuedTraitType trait = traitMap.getTrait(getPrimaryTraitType());
    return trait.isCasteOrFavored();
  }

  @Override
  public boolean isTreeRoot() {
    return parentCharms.size() == 0;
  }

  @Override
  public String toString() {
    return getId();
  }

  public void setGroupId(String expectedGroup) {
    this.groupId = expectedGroup;
  }

  @Override
  public SourceBook[] getSources() {
    return new SourceBook[]{null};
  }

  @Override
  public SourceBook getPrimarySource() {
    return null;
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