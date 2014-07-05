package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.lists.IIdentifiedCasteTraitTypeList;
import net.sf.anathema.hero.traits.model.rules.TraitRulesImpl;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplateMap;

import java.util.ArrayList;
import java.util.List;

public class TraitFactory {

  private Hero hero;

  public TraitFactory(Hero hero) {
    this.hero = hero;
  }
  
  public Trait[] createTraits(IIdentifiedCasteTraitTypeList list, IncrementChecker checker, TraitTemplateMap templateMap) {
	  return createTraits(list, new MonoTypeIncrementChecker<FavorableState>(checker, null), templateMap);
  }

  public Trait[] createTraits(IIdentifiedCasteTraitTypeList list, MappableTypeIncrementChecker<FavorableState> checker, TraitTemplateMap templateMap) {
    List<Trait> newTraits = new ArrayList<>();
    for (TraitType type : list.getAll()) {
      CasteType[] casteTypes = list.getTraitCasteTypes(type);
      Trait trait = createTrait(type, casteTypes, checker, templateMap);
      newTraits.add(trait);
    }
    return newTraits.toArray(new Trait[newTraits.size()]);
  }

  private Trait createTrait(TraitType traitType, CasteType[] casteTypes, MappableTypeIncrementChecker<FavorableState> checker, TraitTemplateMap templateMap) {
    TraitTemplate traitTemplate = templateMap.getTemplate(traitType);
    TraitRules favorableTraitRules = new TraitRulesImpl(traitType, traitTemplate, hero);
    ValueChangeChecker valueChecker = new FriendlyValueChangeChecker();
    return new DefaultTrait(hero, favorableTraitRules, casteTypes, valueChecker, checker);
  }
}