package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.hero.spells.template.SpellsTemplate;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class SorceryInitiationEvaluator {
  private final Collection<SorceryInitiation> initiations;

  public SorceryInitiationEvaluator(Hero hero, SpellsTemplate template, HeroEnvironment environment) {
    this.initiations = environment.getObjectFactory().instantiateAllImplementers(SorceryInitiation.class, hero,
            template);
  }

  public boolean isInitiated(CircleType circle) {
    return initiations.stream().anyMatch(initiation -> initiation.isInitiated(circle));
  }

  public boolean canInitiate() {
    return initiations.stream().anyMatch(SorceryInitiation::canInitiate);
  }

  public Collection<CircleType> getCirclesToInitiateInto() {
    return initiations.stream().flatMap(
            (initiation) -> initiation.getCirclesToInitiateInto().stream()).distinct().collect(toList());
  }
}