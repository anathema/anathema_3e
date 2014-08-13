package net.sf.anathema.hero.abilities.model;

import com.google.common.base.Functions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.hero.abilities.template.AbilitiesTemplate;
import net.sf.anathema.hero.abilities.template.CasteTraitTemplate;
import net.sf.anathema.hero.concept.model.concept.CasteCollection;
import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
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
import net.sf.anathema.hero.traits.model.event.PromoteStateChange;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.hero.traits.model.group.GroupedTraitTypeBuilder;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.traits.model.rules.TraitRulesImpl;
import net.sf.anathema.hero.traits.model.state.Castes;
import net.sf.anathema.hero.traits.model.state.DefaultTraitStateType;
import net.sf.anathema.hero.traits.model.state.MappableTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.NoRequiredState;
import net.sf.anathema.hero.traits.model.state.RequiredFavored;
import net.sf.anathema.hero.traits.model.state.RequiredTraitState;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateImpl;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.hero.traits.model.state.TraitStateTypes;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.template.TraitTemplateMapImpl;
import net.sf.anathema.library.identifier.Identifier;

import java.util.HashMap;
import java.util.Map;

public class AbilitiesModelImpl extends DefaultTraitMap implements AbilitiesModel, HeroModel {

  private IdentifiedTraitTypeList[] abilityTraitGroups;
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
    this.abilityTraitGroups = createAbilityGroups(hero);
    createAndAddTraits();
  }

  private IdentifiedTraitTypeList[] createAbilityGroups(Hero hero) {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    CasteCollection casteCollection = concept.getCasteCollection();
    GroupedTraitType[] abilityGroups = GroupedTraitTypeBuilder.BuildFor(template,
      AllAbilityTraitTypeList.getInstance());
    return new AbilityTypeGroupFactory().createTraitGroups(casteCollection, abilityGroups);
  }

  private void createAndAddTraits() {
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
    Castes castes = getCastesFor(trait.getType());
    RequiredTraitState requiredState = findRequiredState(traitRules);
    return new TraitStateImpl(hero, castes, checker, requiredState);
  }

  private RequiredTraitState findRequiredState(TraitRules traitRules) {
    if (traitRules.isRequiredFavored()) {
      return new RequiredFavored();
    } else {
      return new NoRequiredState();
    }
  }

  @Override
  public void initializeListening(ChangeAnnouncer changeAnnouncer) {
    for (Trait ability : getAll()) {
      traitStateMap.addTraitStateChangedListener(ability, new PromoteStateChange(changeAnnouncer));
      ability.addCurrentValueListener(new TraitValueChangedListener(changeAnnouncer, ability));
    }
  }

  private MappableTypeIncrementChecker<TraitStateType> createStateIncrementChecker() {
    Map<TraitStateType, Integer> stateLimits = new HashMap<>();
    for (TraitStateType state : getAvailableTraitStates()) {
      if (state == DefaultTraitStateType.Default) {
        stateLimits.put(state, MappableTypeIncrementChecker.NO_LIMIT);
      } else {
        stateLimits.put(state, getTraitPicksForState(state));
      }
    }
    return new StatePickIncrementChecker(stateLimits, traitStateMap);
  }

  @Override
  public IdentifiedTraitTypeList[] getGroups() {
    return abilityTraitGroups;
  }

  @Override
  public int getTraitMaximum() {
    TraitLimitation limitation = traitModel.createLimitation(template.standard.limitation);
    return limitation.getAbsoluteLimit(hero);
  }

  @SuppressWarnings("ConstantConditions")
  @Override
  public int getTraitPicksForState(TraitStateType state) {
    return Functions.forMap(template.picks, 0).apply(state.getId());
  }

  @Override
  public Iterable<TraitStateType> getAvailableTraitStates() {
    return traitStateTypes;
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
