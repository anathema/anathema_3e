package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.FriendlyValueChangeChecker;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitImpl;
import net.sf.anathema.hero.traits.model.TraitRules;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValueChangeChecker;
import net.sf.anathema.hero.traits.model.rules.TraitRulesImpl;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplateMap;

import java.util.ArrayList;
import java.util.List;

public class SpiritualTraitFactory {

  private Hero hero;
  private final TraitTemplateMap traitTemplateMap;

  public SpiritualTraitFactory(Hero hero, TraitTemplateMap traitTemplateMap) {
    this.hero = hero;
    this.traitTemplateMap = traitTemplateMap;
  }

  public Trait createTrait(TraitType traitType) {
    TraitTemplate traitTemplate = traitTemplateMap.getTemplate(traitType);
    TraitRules rules = new TraitRulesImpl(traitType, traitTemplate, hero);
    return new TraitImpl(hero, rules);
  }
}