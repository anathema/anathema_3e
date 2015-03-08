package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.hero.spells.template.CircleInitiationTemplate;
import net.sf.anathema.hero.spells.template.SpellsTemplate;

import java.util.Collection;

@SuppressWarnings("UnusedDeclaration") //Used through reflection in SorceryInitiationEvaluator
public class CharmInitiation implements SorceryInitiation {
  private final Hero hero;
  private final SpellsTemplate template;

  public CharmInitiation(Hero hero, SpellsTemplate template) {
    this.hero = hero;
    this.template = template;
  }

  public boolean isInitiated(CircleType circle) {
    CircleInitiationTemplate initiation = template.charmInitiations.get(circle);
    return initiation != null && initiation.isInitiated(hero);
  }

  @Override
  public boolean canInitiate() {
    return !template.charmInitiations.isEmpty();
  }

  @Override
  public Collection<CircleType> getCirclesToInitiateInto() {
    return template.charmInitiations.keySet();
  }
}