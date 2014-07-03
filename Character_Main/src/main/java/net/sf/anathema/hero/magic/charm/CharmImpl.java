package net.sf.anathema.hero.magic.charm;

import com.google.common.base.Preconditions;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.old.cost.CostList;
import net.sf.anathema.charm.old.source.SourceBook;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.magic.basic.AbstractMagic;
import net.sf.anathema.hero.magic.charm.duration.Duration;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.DirectCharmPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.SimpleCharmPrerequisite;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.magic.parser.charms.CharmPrerequisiteList;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static net.sf.anathema.hero.traits.model.types.AbilityType.MartialArts;

public class CharmImpl extends AbstractMagic implements Charm, CharmParent {

  private final CharmPrerequisiteList prerequisisteList;

  private final Duration duration;
  private final SourceBook[] sources;
  private final CostList temporaryCost;
  private final List<CharmImpl> children = new ArrayList<>();
  private final List<CharmPrerequisite> prerequisites = new ArrayList<>();
  private final Set<String> favoredCasteIds = new HashSet<>();

  private final CharmType charmType;
  private TreeReference treeReference;
  private CharmName name;

  public CharmImpl(TreeReference treeReference, CharmName name, CharmPrerequisiteList prerequisiteList,
                   CostList temporaryCost, Duration duration, CharmType charmType,
                   SourceBook[] sources) {
    Preconditions.checkNotNull(prerequisiteList);
    Preconditions.checkNotNull(treeReference);
    Preconditions.checkNotNull(name);
    Preconditions.checkNotNull(temporaryCost);
    Preconditions.checkNotNull(duration);
    Preconditions.checkNotNull(charmType);
    Preconditions.checkNotNull(sources);
    this.name = name;
    this.treeReference = treeReference;
    this.prerequisisteList = prerequisiteList;
    this.temporaryCost = temporaryCost;
    this.duration = duration;
    this.charmType = charmType;
    this.sources = sources;
  }

  @Override
  public CharmName getName() {
    return name;
  }

  @Override
  public CharmType getCharmType() {
    return charmType;
  }

  @Override
  public TreeReference getTreeReference() {
    return treeReference;
  }

  @Override
  public Duration getDuration() {
    return duration;
  }

  @Override
  public ValuedTraitType getEssence() {
    return prerequisisteList.getEssencePrerequisite();
  }

  @Override
  public ValuedTraitType[] getPrerequisites() {
    return prerequisisteList.getTraitPrerequisites();
  }

  @Override
  public SourceBook[] getSources() {
    return sources.length > 0 ? sources : null;
  }

  @Override
  public SourceBook getPrimarySource() {
    return sources.length > 0 ? sources[0] : null;
  }

  @Override
  public CostList getTemporaryCost() {
    return temporaryCost;
  }

  public void extractParentCharms(UnlinkedCharmMap unlinkedCharms) {
    prerequisites.addAll(Arrays.asList(prerequisisteList.getCharmPrerequisites()));
    for (CharmPrerequisite prerequisite : prerequisites) {
      prerequisite.link(unlinkedCharms);
    }
    List<DirectCharmPrerequisite> directPrerequisites = getPrerequisitesOfType(DirectCharmPrerequisite.class);
    for (DirectCharmPrerequisite prerequisite : directPrerequisites) {
      Charm[] charms = prerequisite.getDirectPredecessors();
      for (Charm charm : charms) {
        ((CharmParent) charm).addChild(this);
      }
    }
  }

  @Override
  public List<CharmPrerequisite> getCharmPrerequisites() {
    return prerequisites;
  }

  @Override
  public void addChild(CharmImpl child) {
    children.add(child);
  }

  @Override
  public void forEachChild(Consumer<Charm> consumer) {
    children.forEach(consumer);
  }

  @Override
  public void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer) {
    prerequisites.forEach(consumer);
  }

  @Override
  public Set<Charm> getPrerequisiteCharms(ICharmLearnArbitrator learnArbitrator) {
    Set<Charm> prerequisiteCharms = new HashSet<>();
    for (DirectCharmPrerequisite prerequisite : getPrerequisitesOfType(DirectCharmPrerequisite.class)) {
      prerequisiteCharms.addAll(Arrays.asList(prerequisite.getLearnPrerequisites(learnArbitrator)));
    }
    return prerequisiteCharms;
  }

  public void addFavoredCasteId(String casteId) {
    favoredCasteIds.add(casteId);
  }

  @Override
  public boolean isFavored(Hero hero) {
    if (isSpecialFavoredForCaste(hero)) {
      return true;
    }
    if (isFavoredMartialArts(hero)) {
      return true;
    }
    return isPrimaryTraitFavored(hero);
  }

  private boolean isPrimaryTraitFavored(Hero hero) {
    TraitModel traitModel = TraitModelFetcher.fetch(hero);
    Trait primaryTrait = traitModel.getTrait(getPrimaryTraitType());
    return primaryTrait.isCasteOrFavored();
  }

  private boolean isFavoredMartialArts(Hero hero) {
    Trait martialArts = TraitModelFetcher.fetch(hero).getTrait(MartialArts);
    boolean isMartialArts = hasAttribute(new SimpleIdentifier("MartialArts"));
    return isMartialArts && martialArts.isCasteOrFavored();
  }

  private boolean isSpecialFavoredForCaste(Hero hero) {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    String casteId = concept.getCaste().getType().getId();
    return favoredCasteIds.contains(casteId);
  }

  @Override
  public TraitType getPrimaryTraitType() {
    return getPrerequisites().length == 0 ? OtherTraitType.Essence : getPrerequisites()[0].getType();
  }

  private <T extends CharmPrerequisite> List<T> getPrerequisitesOfType(Class<T> clazz) {
    List<T> matches = new ArrayList<>();
    for (CharmPrerequisite prerequisite : prerequisites) {
      if (clazz.isInstance(prerequisite)) {
        matches.add((T) prerequisite);
      }
    }
    return matches;
  }

  public void addParentCharms(Charm... parent) {
    for (Charm charm : parent) {
      prerequisites.add(new SimpleCharmPrerequisite(charm));
    }
  }
}