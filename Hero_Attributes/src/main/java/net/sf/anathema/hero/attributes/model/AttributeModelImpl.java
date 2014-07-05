package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.hero.concept.model.concept.CasteCollection;
import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.DefaultTraitMap;
import net.sf.anathema.hero.traits.model.GroupedTraitType;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitGroup;
import net.sf.anathema.hero.traits.model.TraitImpl;
import net.sf.anathema.hero.traits.model.TraitLimitation;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitRules;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.hero.traits.model.group.GroupedTraitTypeBuilder;
import net.sf.anathema.hero.traits.model.lists.IIdentifiedCasteTraitTypeList;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.traits.model.rules.TraitRulesImpl;
import net.sf.anathema.hero.traits.model.state.GrumpyIncrementChecker;
import net.sf.anathema.hero.traits.model.state.IncrementChecker;
import net.sf.anathema.hero.traits.model.state.MappableTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.MonoTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.NullTraitStateMap;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateMap;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.hero.traits.model.types.AttributeGroupType;
import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplateMap;
import net.sf.anathema.hero.traits.template.TraitTemplateMapImpl;
import net.sf.anathema.library.change.ChangeAnnouncer;
import net.sf.anathema.library.identifier.Identifier;

import java.util.ArrayList;
import java.util.List;

public class AttributeModelImpl extends DefaultTraitMap implements AttributeModel, HeroModel {

  private IIdentifiedCasteTraitTypeList[] attributeTraitGroups;
  private Hero hero;
  private GroupedTraitType[] abilityGroups;
  private GroupedTraitsTemplate template;
  private TraitModel traitModel;

  public AttributeModelImpl(GroupedTraitsTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.hero = hero;
    CasteCollection casteCollection = HeroConceptFetcher.fetch(hero).getCasteCollection();
    this.abilityGroups = GroupedTraitTypeBuilder.BuildFor(template, AllAttributeTraitTypeList.getInstance());
    this.attributeTraitGroups = new AttributeTypeGroupFactory().createTraitGroups(casteCollection,
      getAttributeGroups());
    addAttributes();
    this.traitModel = TraitModelFetcher.fetch(hero);
    traitModel.addTraits(getAll());
  }

  private void addAttributes() {
    IncrementChecker incrementChecker = new GrumpyIncrementChecker();
    for (IIdentifiedCasteTraitTypeList traitGroup : attributeTraitGroups) {
      TraitTemplateMap map = new TraitTemplateMapImpl(template);
      Trait[] traits = createTraits(traitGroup, new MonoTypeIncrementChecker<>(incrementChecker, null), map);
      addTraits(traits);
    }
  }

  private TraitImpl[] createTraits(IIdentifiedCasteTraitTypeList list,
                                  MappableTypeIncrementChecker<TraitStateType> checker, TraitTemplateMap templateMap) {
    List<Trait> newTraits = new ArrayList<>();
    for (TraitType type : list.getAll()) {
      CasteType[] casteTypes = list.getTraitCasteTypes(type);
      TraitTemplate traitTemplate = templateMap.getTemplate(type);
      TraitRules traitRules = new TraitRulesImpl(type, traitTemplate, hero);
      Trait trait = new TraitImpl(hero, traitRules);
      newTraits.add(trait);
    }
    return newTraits.toArray(new TraitImpl[newTraits.size()]);
  }

  @Override
  public GroupedTraitType[] getAttributeGroups() {
    return abilityGroups;
  }

  @Override
  public TraitStateMap getStateMap() {
    // todo sandra favorable attributes?
    return new NullTraitStateMap();
  }

  @Override
  public int getTraitMaximum() {
    TraitLimitation limitation = traitModel.createLimitation(template.standard.limitation);
    return limitation.getAbsoluteLimit(hero);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    for (Trait attribute : getAll()) {
      attribute.addCurrentValueListener(new TraitValueChangedListener(announcer, attribute));
    }
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    TraitGroup[] groups = new TraitGroup[attributeTraitGroups.length];
    for (int index = 0; index < groups.length; index++) {
      final IIdentifiedCasteTraitTypeList typeGroup = attributeTraitGroups[index];
      groups[index] = new MappedTraitGroup(this, typeGroup);
    }
    return groups;
  }

  @Override
  public IdentifiedTraitTypeList[] getTraitTypeList() {
    return attributeTraitGroups;
  }

  public Trait[] getAll(AttributeGroupType groupType) {
    for (IdentifiedTraitTypeList group : getTraitTypeList()) {
      if (group.getListId().equals(groupType)) {
        List<TraitType> all = group.getAll();
        return getTraits(all.toArray(new TraitType[all.size()]));
      }
    }
    return new Trait[0];
  }

  @Override
  public TraitState getState(TraitType traitType) {
    return getState(getTrait(traitType));
  }

  @Override
  public TraitState getState(Trait trait) {
    return getStateMap().getState(trait);
  }
}
