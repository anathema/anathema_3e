package net.sf.anathema.hero.magic.dummy;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.martial.MartialArtsLevel;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostEvaluator;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.PrintMagicProvider;
import net.sf.anathema.hero.charms.model.favored.FavoredChecker;
import net.sf.anathema.hero.charms.model.favored.IsFavoredMagic;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnListener;
import net.sf.anathema.hero.charms.model.learn.LearningCharmTree;
import net.sf.anathema.hero.charms.model.learn.MagicLearner;
import net.sf.anathema.hero.charms.model.options.CharmOptions;
import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.sheet.content.IMagicStats;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

import java.util.ArrayList;
import java.util.List;

public class DummyCharmsModel implements CharmsModel, CharmOptions {

  private Charm[] charms = new Charm[0];

  private LearningCharmTree[] groups;
  private IsFavoredMagic favoredMagic = new IsFavoredMagic();

  @Override
  public void addLearnableListener(ChangeListener listener) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearned(Charm charm) {
    throw new NotYetImplementedException();
  }

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearned(CharmName charmId) {
    throw new NotYetImplementedException();
  }

  @Override
  public LearningCharmTree[] getCharmGroups(CategoryReference category) {
    return groups;
  }

  @Override
  public Charm[] getLearnedCharms(boolean experienced) {
    return charms;
  }

  @Override
  public boolean isLearnable(Charm charm) {
    throw new NotYetImplementedException();
  }
  
  @Override
  public boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute,
		  int threshold) {
	throw new NotYetImplementedException();
  }

  @Override
  public Charm getCharmById(CharmName charmId) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isCompulsiveCharm(Charm charm) {
    return false;
  }

  @Override
  public LearningCharmTree[] getAllGroups() {
    throw new NotYetImplementedException();
  }

  @Override
  public void forgetAllAlienCharms() {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isAlienCharm(Charm charm) {
    for (Charm currentCharm : charms) {
      if (charm == currentCharm) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isFavoredMagic(Magic magic) {
    return favoredMagic.isFavored(magic);
  }

  @Override
  public CharmSpecialsModel getCharmSpecialsModel(Charm charm) {
    return null;
  }

  @Override
  public LearningCharmTree getGroup(Charm charm) {
    throw new NotYetImplementedException();
  }

  @Override
  public Charm[] filterAvailableCharms(CharmTree charmGroup) {
    return charmGroup.getAllCharms();
  }

  @Override
  public boolean isAlienCharmsAllowedForHero() {
    return false;
  }

  @Override
  public List<CategoryReference> getValidCategoryReferencesForHero() {
    return new ArrayList<>();
  }

  @Override
  public CharmMap getCharmIdMap() {
    throw new NotYetImplementedException();
  }

  @Override
  public ISpecialCharm[] getSpecialCharms() {
    return new ISpecialCharm[0];
  }

  @Override
  public void addFavoredChecker(FavoredChecker favoredChecker) {
    favoredMagic.add(favoredChecker);
  }

  @Override
  public void addPrintProvider(PrintMagicProvider provider) {
    // nothing to do
  }

  @Override
  public void addLearnProvider(MagicLearner provider) {
    // nothing to do
  }

  @Override
  public MagicCreationCostEvaluator getMagicCostEvaluator() {
    return null;
  }

  @Override
  public CharmOptions getOptions() {
    return this;
  }

  @Override
  public MartialArtsLevel getStandardMartialArtsLevel() {
    return MartialArtsLevel.Mortal;
  }

  public void setGroups(LearningCharmTree... groups) {
    this.groups = groups;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  @Override
  public void addPrintMagic(List<IMagicStats> printMagic) {
    // nothing to do
  }
}