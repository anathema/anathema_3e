package net.sf.anathema.hero.abilities.model;

import com.google.common.base.Functions;
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
import net.sf.anathema.hero.traits.model.group.TraitTypeGroupFactory;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.traits.model.rules.TraitRulesImpl;
import net.sf.anathema.hero.traits.model.state.Castes;
import net.sf.anathema.hero.traits.model.state.MappableTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.NoRequiredState;
import net.sf.anathema.hero.traits.model.state.RequiredFavored;
import net.sf.anathema.hero.traits.model.state.RequiredTraitState;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateImpl;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.hero.traits.model.state.TraitStateTypes;
import net.sf.anathema.hero.traits.model.types.AbilityTraitType;
import net.sf.anathema.hero.traits.template.Group;
import net.sf.anathema.hero.traits.template.TraitTemplateMapImpl;
import net.sf.anathema.library.identifier.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.hero.traits.model.state.DefaultTraitStateType.Default;
import static net.sf.anathema.hero.traits.model.state.MappableTypeIncrementChecker.NO_LIMIT;

public class AbilitiesModelImpl extends DefaultTraitMap implements AbilitiesModel, HeroModel {

  private final TraitStateTypes traitStateTypes;
  private final AbilitiesTemplate template;
  private final TraitTemplateMapImpl templateMap;
  private IdentifiedTraitTypeList[] abilityTraitGroups;
  private Hero hero;
  private TraitModel traitModel;
  private TraitStateMapImpl traitStateMap;
  private AllAbilityTraitTypeList abilityTypes;

  public AbilitiesModelImpl(AbilitiesTemplate template) {
    this.template = template;
    this.templateMap = new TraitTemplateMapImpl(template);
    List<String> configuredStates = new ArrayList<>();
    configuredStates.add(Default.getId());
    configuredStates.addAll(template.picks.keySet());
    this.traitStateTypes = TraitStateTypes.limitedTo(configuredStates);
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
    this.abilityTypes = fillAbilityTypeList();
    this.abilityTraitGroups = createAbilityGroups();
    createAndAddTraits();
  }

  private AllAbilityTraitTypeList fillAbilityTypeList() {
    List<TraitType> abilityTypes = new ArrayList<>();
    for (Group group : template.groups) {
      for (String traitId : group.traits) {
        abilityTypes.add(new AbilityTraitType(traitId));
      }
    }
    return new AllAbilityTraitTypeList(abilityTypes);
  }

  private IdentifiedTraitTypeList[] createAbilityGroups() {
    GroupedTraitType[] abilityGroups = GroupedTraitTypeBuilder.BuildFor(template, abilityTypes);
    return new TraitTypeGroupFactory().createTraitGroups(abilityGroups);
  }

  private void createAndAddTraits() {
    for (TraitType type : abilityTypes.getAll()) {
      TraitRules traitRules = new TraitRulesImpl(type, templateMap.getTemplate(type), this.hero);
      Trait trait = new TraitImpl(this.hero, traitRules);
      addTraits(trait);
      traitStateMap.addState(trait, createTraitState(traitRules, trait));
    }
    traitModel.addTraits(getAll());
  }

  private Castes getCastesFor(TraitType traitType) {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    CasteCollection casteCollection = concept.getCasteCollection();
    Castes casteTypes = new Castes(hero);
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
    return new TraitStateImpl(hero, castes, checker, requiredState, traitStateTypes);
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
      int picksForState = getTraitPicksForState(state);
      if (picksForState == 0) {
        picksForState = NO_LIMIT;
      }
      stateLimits.put(state, picksForState);
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
  public List<TraitType> getAllAbilityTypes() {
    return abilityTypes.getAll();
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
