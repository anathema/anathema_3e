package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.abilities.template.AbilitiesTemplate;
import net.sf.anathema.hero.abilities.template.CasteTraitTemplate;
import net.sf.anathema.hero.concept.model.concept.CasteCollection;
import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.DefaultTraitMap;
import net.sf.anathema.hero.traits.model.GroupedTraitType;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitImpl;
import net.sf.anathema.hero.traits.model.TraitLimitation;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitRules;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.event.FavoredChangedListener;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.hero.traits.model.group.GroupedTraitTypeBuilder;
import net.sf.anathema.hero.traits.model.lists.IIdentifiedCasteTraitTypeList;
import net.sf.anathema.hero.traits.model.rules.TraitRulesImpl;
import net.sf.anathema.hero.traits.model.state.MappableTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateImpl;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.template.TraitTemplateMapImpl;
import net.sf.anathema.library.change.ChangeAnnouncer;
import net.sf.anathema.library.identifier.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbilitiesModelImpl extends DefaultTraitMap implements AbilitiesModel, HeroModel {

  private IIdentifiedCasteTraitTypeList[] abilityTraitGroups;
  private Hero hero;
  private AbilitiesTemplate template;
  private TraitModel traitModel;
  private TraitStateMapImpl traitStateMap;
  private final TraitTemplateMapImpl templateMap;

  public AbilitiesModelImpl(AbilitiesTemplate template) {
    this.template = template;
    this.templateMap = new TraitTemplateMapImpl(template);
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.traitStateMap = new TraitStateMapImpl(hero);
    this.hero = hero;
    this.traitModel = TraitModelFetcher.fetch(hero);
    createAndAddTraits(hero);
  }

  private void createAndAddTraits(Hero hero) {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    CasteCollection casteCollection = concept.getCasteCollection();
    GroupedTraitType[] abilityGroups = GroupedTraitTypeBuilder.BuildFor(template, AllAbilityTraitTypeList.getInstance());
    this.abilityTraitGroups = new AbilityTypeGroupFactory().createTraitGroups(casteCollection, abilityGroups);
    for (AbilityType type : AbilityType.values()) {
      TraitRules traitRules = new TraitRulesImpl(type, templateMap.getTemplate(type), this.hero);
      Trait trait = new TraitImpl(this.hero, traitRules);
      addTraits(trait);
      traitStateMap.addState(trait, createTraitState(traitRules, trait));
    }
    traitModel.addTraits(getAll());
  }

  private List<CasteType> getCastesFor(TraitType traitType) {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    CasteCollection casteCollection = concept.getCasteCollection();
    List<CasteType> casteTypes = new ArrayList<>();
    for (CasteTraitTemplate casteAbilitiesTemplate : template.casteAbilities) {
      if (casteAbilitiesTemplate.traits.contains(traitType.getId())) {
        casteTypes.add(casteCollection.getById(casteAbilitiesTemplate.caste));
      }
    }
    return casteTypes;
  }

  private TraitStateImpl createTraitState(TraitRules traitRules, Trait trait) {
    MappableTypeIncrementChecker<TraitStateType> checker = createStateIncrementChecker();
    boolean requiredFavored = traitRules.isRequiredFavored();
    List<CasteType> castes = getCastesFor(trait.getType());
    return new TraitStateImpl(this.hero, castes, checker, trait, requiredFavored);
  }

  @Override
  public void initializeListening(ChangeAnnouncer changeAnnouncer) {
    for (Trait ability : getAll()) {
      traitStateMap.addTraitStateChangedListener(ability, new FavoredChangedListener(changeAnnouncer));
      ability.addCurrentValueListener(new TraitValueChangedListener(changeAnnouncer, ability));
    }
  }

  private MappableTypeIncrementChecker<TraitStateType> createStateIncrementChecker() {
    Map<TraitStateType, Integer> stateLimits = new HashMap<>();
    for (TraitStateType state : TraitStateType.values()) {
      if (state == TraitStateType.Default) {
        stateLimits.put(state, MappableTypeIncrementChecker.NO_LIMIT);
      } else {
        stateLimits.put(state, getTraitPicksForState(state));
      }
    }
    return new StatePickIncrementChecker(stateLimits, traitStateMap);
  }

  @Override
  public IIdentifiedCasteTraitTypeList[] getTraitTypeList() {
    return abilityTraitGroups;
  }

  @Override
  public int getTraitMaximum() {
    TraitLimitation limitation = traitModel.createLimitation(template.standard.limitation);
    return limitation.getAbsoluteLimit(hero);
  }

  @Override
  public int getTraitPicksForState(TraitStateType state) {
    switch (state) {
      case Favored:
        return template.favoredCount;
      case Caste:
        return template.casteCount;
      case Supernal:
        return template.supernalCount;
      default:
        return 0;
    }
  }

  @Override
  public TraitState getState(TraitType traitType) {
    Trait ability = getTrait(traitType);
    return getState(ability);
  }

  @Override
  public TraitState getState(Trait trait) {
    return traitStateMap.getState(trait);
  }
}
