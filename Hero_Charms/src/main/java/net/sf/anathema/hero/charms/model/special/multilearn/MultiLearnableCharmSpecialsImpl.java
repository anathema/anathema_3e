package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.hero.charms.display.special.CharmSpecialistImpl;
import net.sf.anathema.hero.charms.model.CharmTraitRequirementChecker;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.special.CharmSpecialist;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmLearnListener;
import net.sf.anathema.hero.charms.model.special.prerequisite.PrerequisiteModifyingCharms;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.learn.CharmLearnableArbitrator;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.hero.traits.model.DefaultTraitType;
import net.sf.anathema.hero.traits.model.IncrementChecker;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitChangeFlavor;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.rules.LimitedTrait;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplateFactory;
import net.sf.anathema.lib.control.IntValueChangedListener;
import net.sf.anathema.lib.data.Range;
import org.jmock.example.announcer.Announcer;

public class MultiLearnableCharmSpecialsImpl implements MultiLearnCharmSpecials {

  private final Announcer<ISpecialCharmLearnListener> control = Announcer.to(ISpecialCharmLearnListener.class);
  private final Trait trait;
  private CharmsModel config;
  private Charm charm;
  private IMultiLearnableCharm specialCharm;
  private CharmSpecialist specialist;
  private CharmLearnableArbitrator arbitrator;
  private Hero hero;

  public MultiLearnableCharmSpecialsImpl(Hero hero, CharmsModel config, Charm charm, IMultiLearnableCharm specialCharm,
                                         CharmLearnableArbitrator arbitrator) {
    this.hero = hero;
    this.specialist = new CharmSpecialistImpl(hero);
    this.config = config;
    this.charm = charm;
    this.specialCharm = specialCharm;
    this.arbitrator = arbitrator;
    TraitTemplate template = TraitTemplateFactory.createStaticLimitedTemplate(0, specialCharm.getAbsoluteLearnLimit());
    this.trait = new LimitedTrait(hero, new DefaultTraitType(charm.getName().text), template, new MultiLearnableIncrementChecker());
    this.trait.addCurrentValueListener(new IntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        fireLearnCountChanged(newValue);
      }
    });
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor instanceof TraitChangeFlavor) {
          validateLearnCount();
        }
      }
    });
  }

  @Override
  public void forget() {
    trait.setCurrentValue(0);
  }

  @Override
  public void learn(boolean experienced) {
    int minimumLearnCount = specialCharm.getMinimumLearnCount(createLearnRangeContext());
    if (experienced) {
      if (getCurrentLearnCount() == 0) {
        trait.setExperiencedValue(minimumLearnCount);
      }
    } else if (getCreationLearnCount() == 0) {
      trait.setCreationValue(minimumLearnCount);
    }
  }

  @Override
  public int getCreationLearnCount() {
    return trait.getCreationValue();
  }

  @Override
  public void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener) {
    control.addListener(listener);
  }

  private void fireLearnCountChanged(int learnCount) {
    control.announce().learnCountChanged(learnCount);
  }

  @Override
  public Charm getCharm() {
    return charm;
  }

  @Override
  public Trait getCategory() {
    return trait;
  }

  @Override
  public int getCurrentLearnCount() {
    return trait.getCurrentValue();
  }

  @Override
  public void setCurrentLearnCount(int newValue) {
    trait.setCurrentValue(newValue);
  }

  private void validateLearnCount() {
    if (trait.getCurrentValue() == 0) {
      return;
    }
    Range range = getRange();
    if (trait.getCurrentValue() < range.getLowerBound()) {
      setCurrentLearnCount(range.getLowerBound());
    }
    if (trait.getCurrentValue() > range.getUpperBound()) {
      setCurrentLearnCount(range.getUpperBound());
    }
  }

  private Range getRange() {
    int minValue = specialCharm.getMinimumLearnCount(createLearnRangeContext());
    int maxValue = specialCharm.getMaximumLearnCount(createLearnRangeContext());
    return new Range(minValue, maxValue);
  }

  private LearnRangeContext createLearnRangeContext() {
    PrerequisiteModifyingCharms modifyingCharms = new PrerequisiteModifyingCharms(config.getOptions().getSpecialCharms());
    CharmTraitRequirementChecker requirementChecker = new CharmTraitRequirementChecker(modifyingCharms, specialist.getTraits(), config);
    return new LearnRangeContext(TraitModelFetcher.fetch(hero), requirementChecker, charm);
  }

  private class MultiLearnableIncrementChecker implements IncrementChecker {
    @Override
    public boolean isValidIncrement(int increment) {
      int incrementedValue = MultiLearnableCharmSpecialsImpl.this.trait.getCurrentValue() + increment;
      if (incrementedValue == 0) {
        return true;
      }
      boolean learnable = arbitrator.isLearnable(charm);
      return learnable && getRange().contains(incrementedValue);
    }
  }
}