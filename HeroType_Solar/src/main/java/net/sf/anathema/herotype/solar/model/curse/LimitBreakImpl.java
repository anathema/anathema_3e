package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.rules.LimitedTraitImpl;
import net.sf.anathema.hero.traits.model.rules.ModificationType;
import net.sf.anathema.hero.traits.model.state.FriendlyIncrementChecker;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplateFactory;

public class LimitBreakImpl implements LimitBreak {

  private Trait limitTrait;
  private Hero hero;

  public LimitBreakImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public Trait getLimitTrait() {
    if (limitTrait == null) {
      TraitType traitType = new TraitType(getLimitString());
      TraitTemplate limitedTemplate = TraitTemplateFactory.createStaticLimitedTemplate(0, 10, ModificationType.Free);
      limitTrait = new LimitedTraitImpl(hero, traitType, limitedTemplate, new FriendlyIncrementChecker());
    }
    return limitTrait;
  }

  protected String getLimitString() {
    return "VirtueFlaw.LimitTrait";
  }
}