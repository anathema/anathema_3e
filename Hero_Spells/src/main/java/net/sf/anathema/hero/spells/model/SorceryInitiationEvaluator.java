package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.hero.spells.template.SpellsTemplate;

import java.util.Collection;

public class SorceryInitiationEvaluator {
  private final Collection<SorceryInitiation> initiations;

  public SorceryInitiationEvaluator(Hero hero, SpellsTemplate template, HeroEnvironment environment) {
    this.initiations = environment.getObjectFactory().instantiateAllImplementers(SorceryInitiation.class, hero, template);
  }

  public boolean isInitiated(CircleType circle) {
    for (SorceryInitiation initiation : initiations) {
      if (initiation.isInitiated(circle)){
        return true;
      }
    }
    return false;
  }
}