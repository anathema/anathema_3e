package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.lists.IIdentifiedCasteTraitTypeList;
import net.sf.anathema.hero.traits.model.rules.TraitRulesImpl;
import net.sf.anathema.hero.traits.model.state.IncrementChecker;
import net.sf.anathema.hero.traits.model.state.MappableTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.MonoTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplateMap;

import java.util.ArrayList;
import java.util.List;

public class TraitFactory {

  private Hero hero;

  public TraitFactory(Hero hero) {
    this.hero = hero;
  }
  
  public TraitImpl[] createTraits(IIdentifiedCasteTraitTypeList list, IncrementChecker checker, TraitTemplateMap templateMap) {
	  return createTraits(list, new MonoTypeIncrementChecker<TraitStateType>(checker, null), templateMap);
  }

  public TraitImpl[] createTraits(IIdentifiedCasteTraitTypeList list, MappableTypeIncrementChecker<TraitStateType> checker, TraitTemplateMap templateMap) {
    List<Trait> newTraits = new ArrayList<>();
    for (TraitType type : list.getAll()) {
      CasteType[] casteTypes = list.getTraitCasteTypes(type);
      Trait trait = createTrait(type, casteTypes, checker, templateMap);
      newTraits.add(trait);
    }
    return newTraits.toArray(new TraitImpl[newTraits.size()]);
  }

  private TraitImpl createTrait(TraitType traitType, CasteType[] casteTypes, MappableTypeIncrementChecker<TraitStateType> checker, TraitTemplateMap templateMap) {
    TraitTemplate traitTemplate = templateMap.getTemplate(traitType);
    TraitRules favorableTraitRules = new TraitRulesImpl(traitType, traitTemplate, hero);
    ValueChangeChecker valueChecker = new FriendlyValueChangeChecker();
    return new TraitImpl(hero, favorableTraitRules, casteTypes, valueChecker, checker);
  }
}