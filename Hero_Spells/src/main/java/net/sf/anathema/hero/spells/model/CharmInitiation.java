package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.hero.spells.template.SpellsTemplate;
import net.sf.anathema.magic.data.reference.CharmName;

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
    String charmId = template.charmInitiations.get(circle);
    if (charmId == null) {
      return false;
    }
    CharmName charmName = new CharmName(charmId);
    return CharmsModelFetcher.fetch(hero).isLearned(charmName);
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