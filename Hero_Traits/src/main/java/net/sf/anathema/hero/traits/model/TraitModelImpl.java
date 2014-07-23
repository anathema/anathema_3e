package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceChange;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.context.CreationTraitValueStrategy;
import net.sf.anathema.hero.traits.model.context.ExperiencedTraitValueStrategy;
import net.sf.anathema.hero.traits.model.context.ProxyTraitValueStrategy;
import net.sf.anathema.hero.traits.model.rules.limitation.LimitationFactory;
import net.sf.anathema.hero.traits.model.rules.minimum.DynamicMinimumMap;
import net.sf.anathema.hero.traits.template.LimitationTemplate;
import net.sf.anathema.library.identifier.Identifier;

import java.util.Collection;
import java.util.Iterator;

public class TraitModelImpl extends DefaultTraitMap implements TraitMap, TraitModel, HeroModel {

  private final ProxyTraitValueStrategy traitValueStrategy = new ProxyTraitValueStrategy(new CreationTraitValueStrategy());
  private DynamicMinimumMap minimumMap = new DynamicMinimumMap();
  private ExperienceModel experience;
  private Collection<LimitationFactory> limitationFactories;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    limitationFactories = environment.getObjectFactory().instantiateAllImplementers(LimitationFactory.class);
    experience = ExperienceModelFetcher.fetch(hero);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    announcer.addListener(flavor -> {
      if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
        boolean experienced = experience.isExperienced();
        updateLearnStrategies(experienced);
      }
    });
  }

  private void updateLearnStrategies(boolean experienced) {
    if (experienced) {
      traitValueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    } else {
      traitValueStrategy.setStrategy(new CreationTraitValueStrategy());
    }
  }

  @Override
  public TraitValueStrategy getValueStrategy() {
     return traitValueStrategy;
  }

  @Override
  public Iterator<Trait> iterator() {
    return getAll().iterator();
  }

  @Override
  public TraitLimitation createLimitation(LimitationTemplate limitation) {
    for (LimitationFactory factory : limitationFactories) {
      if (factory.supports(limitation)) {
        return factory.createLimitation(limitation);
      }
    }
    throw new IllegalArgumentException();
  }

  @Override
  public DynamicMinimumMap getMinimumMap() {
    return minimumMap;
  }
}