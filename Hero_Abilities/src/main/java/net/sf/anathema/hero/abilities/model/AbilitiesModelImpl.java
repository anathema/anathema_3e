package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.abilities.template.AbilitiesTemplate;
import net.sf.anathema.hero.concept.model.concept.CasteCollection;
import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.DefaultTraitMap;
import net.sf.anathema.hero.traits.model.GroupedTraitType;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitFactory;
import net.sf.anathema.hero.traits.model.TraitLimitation;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.event.FavoredChangedListener;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.hero.traits.model.group.GroupedTraitTypeBuilder;
import net.sf.anathema.hero.traits.model.lists.IIdentifiedCasteTraitTypeList;
import net.sf.anathema.hero.traits.model.state.MappableTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.StatePickIncrementChecker;
import net.sf.anathema.hero.traits.model.state.TraitState;
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

  public AbilitiesModelImpl(AbilitiesTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.hero = hero;
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    CasteCollection casteCollection = concept.getCasteCollection();
    GroupedTraitType[] abilityGroups = GroupedTraitTypeBuilder.BuildFor(template, AllAbilityTraitTypeList.getInstance());
    this.abilityTraitGroups = new AbilityTypeGroupFactory().createTraitGroups(casteCollection, abilityGroups);
    MappableTypeIncrementChecker<TraitState> incrementChecker = createFavoredAbilityIncrementChecker(this, abilityGroups);
    TraitFactory traitFactory = new TraitFactory(this.hero);
    for (IIdentifiedCasteTraitTypeList traitGroup : abilityTraitGroups) {
      Trait[] traits = traitFactory.createTraits(traitGroup, incrementChecker, new TraitTemplateMapImpl(template));
      addTraits(traits);
    }
    this.traitModel = TraitModelFetcher.fetch(hero);
    traitModel.addTraits(getAll());
  }

  @Override
  public void initializeListening(ChangeAnnouncer changeAnnouncer) {
    for (Trait ability : getAll()) {
      ability.getFavorization().addTraitStateChangedListener(new FavoredChangedListener(changeAnnouncer));
      ability.addCurrentValueListener(new TraitValueChangedListener(changeAnnouncer, ability));
    }
  }

  private MappableTypeIncrementChecker<TraitState> createFavoredAbilityIncrementChecker(TraitMap traitMap, GroupedTraitType[] abilityGroups) {
    
	Map<TraitState, Integer> stateLimits = new HashMap<>();
	for (TraitState state : TraitState.values()) {
		if (state == TraitState.Default) {
			stateLimits.put(state, MappableTypeIncrementChecker.NO_LIMIT);
		}
		else {
			stateLimits.put(state, getTraitPicksForState(state));
		}
	}
	
    List<TraitType> abilityTypes = new ArrayList<>();
    for (GroupedTraitType traitType : abilityGroups) {
      abilityTypes.add(traitType.getTraitType());
    }
    return new StatePickIncrementChecker(stateLimits, abilityTypes.toArray(new TraitType[abilityTypes.size()]), traitMap);
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
  public int getTraitPicksForState(TraitState state) {
	  switch (state) {
	  case Favored: return template.favoredCount;
	  case Caste: return template.casteCount;
	  case Supernal: return template.supernalCount;
	  default: return 0;
	  }
  }
}
