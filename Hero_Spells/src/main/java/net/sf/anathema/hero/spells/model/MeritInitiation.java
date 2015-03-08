package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.hero.spells.template.SpellsTemplate;
import net.sf.anathema.library.model.OptionalEntryReference;

import java.util.Collection;

@SuppressWarnings("UnusedDeclaration") //Used through reflection in SorceryInitiationEvaluator 
public class MeritInitiation implements SorceryInitiation {
  private final Hero hero;
  private final SpellsTemplate template;

  public MeritInitiation(Hero hero, SpellsTemplate template) {
    this.hero = hero;
    this.template = template;
  }

  @Override
  public boolean isInitiated(CircleType circle) {
    String meritId = template.meritInitiations.get(circle);
    if (meritId == null){
      return false;
    }
    OptionalEntryReference reference = new OptionalEntryReference(meritId);
    return MeritsModelFetcher.fetch(hero).hasMeritsMatchingReference(reference);
  }

  @Override
  public boolean canInitiate() {
    return !template.meritInitiations.isEmpty();
  }

  @Override
  public Collection<CircleType> getCirclesToInitiateInto() {
    return template.meritInitiations.keySet();
  }
}