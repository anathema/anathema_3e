package net.sf.anathema.hero.traits.display;

import net.sf.anathema.hero.environment.herotype.PresentationPropertiesImpl;
import net.sf.anathema.hero.experience.model.ExperienceChange;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitListModel;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.lists.DefaultTraitTypeList;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.library.collection.IdentityMapping;
import net.sf.anathema.library.fx.dot.ExtensibleDotView;
import net.sf.anathema.library.fx.dot.GroupedFavorableDotConfigurationView;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.resources.Resources;

import java.util.List;

import static net.sf.anathema.hero.traits.model.state.TraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.TraitStateType.Favored;

public class FavorableTraitConfigurationPresenter {

  private final GroupedFavorableDotConfigurationView view;
  private final IdentityMapping<Trait, ToggleTool> traitViewsByTrait = new IdentityMapping<>();
  private final Resources resources;
  private final IdentifiedTraitTypeList[] traitTypeGroups;
  private final TraitMap traitConfiguration;
  private Hero hero;
  private TraitListModel traitList;

  public FavorableTraitConfigurationPresenter(TraitListModel traitList, IdentifiedTraitTypeList[] traitTypeGroups, Hero hero, GroupedFavorableDotConfigurationView view,
                                              Resources resources) {
    this.traitList = traitList;
    this.hero = hero;
    this.traitTypeGroups = traitTypeGroups;
    this.traitConfiguration = TraitModelFetcher.fetch(hero);
    this.resources = resources;
    this.view = view;
  }

  public void init(String typePrefix) {
    for (IdentifiedTraitTypeList traitTypeGroup : traitTypeGroups) {
      view.startNewTraitGroup(resources.getString(typePrefix + "." + traitTypeGroup.getListId().getId()));
      List<TraitType> allTraitTypes = traitTypeGroup.getAll();
      addTraitViews(traitConfiguration.getTraits(allTraitTypes.toArray(new TraitType[allTraitTypes.size()])));
    }
    hero.getChangeAnnouncer().addListener(flavor -> {
      if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
        updateButtons();
      }
    });
    updateButtons();
  }

  private void updateButtons() {
    for (Trait trait : getAllTraits()) {
      ToggleTool view = traitViewsByTrait.get(trait);
      boolean disabled = ExperienceModelFetcher.fetch(hero).isExperienced();
      boolean favored = traitList.getTraitState(trait).isCasteOrFavored();
      setButtonState(view, favored, !disabled);
    }
  }

  private Trait[] getAllTraits() {
    return traitConfiguration.getTraits(DefaultTraitTypeList.getAllTraitTypes(traitTypeGroups));
  }

  private void addTraitViews(Trait[] traits) {
    for (Trait trait : traits) {
      addTraitView(trait);
    }
  }

  private void addTraitView(Trait favorableTrait) {
    ExtensibleDotView traitView = createTraitView(favorableTrait);
    addCasteAndFavoredToggle(favorableTrait, traitView);
  }

  private ExtensibleDotView createTraitView(Trait favorableTrait) {
    String traitName = resources.getString(favorableTrait.getType().getId());
    ExtensibleDotView traitView = view.addExtensibleTraitView(traitName, favorableTrait.getMaximalValue());
    new TraitPresenter(favorableTrait, traitView.getIntValueView()).initPresentation();
    return traitView;
  }

  private void addCasteAndFavoredToggle(final Trait favorableTrait, ExtensibleDotView traitView) {
    final ToggleTool casteTool = traitView.addToggleInFront();
    TraitState traitState = traitList.getTraitState(favorableTrait);
    casteTool.setCommand(() -> {
      traitState.advanceFavorableState();
    });
    traitState.addTraitStateChangedListener(state -> updateView(casteTool, state));
    updateView(casteTool, traitState.getType());
    traitViewsByTrait.put(favorableTrait, casteTool);
  }

  private void updateView(final ToggleTool view, TraitStateType state) {
    boolean select = state == Favored || state == Caste;
    boolean enable = true; // state == Favored || state == Default;
    setButtonState(view, select, enable);
    PresentationPropertiesImpl properties = new PresentationPropertiesImpl(hero.getSplat());
    new FavoredIconSelector(view, properties).setIconFor(hero, state);
  }

  private void setButtonState(ToggleTool view, boolean select, boolean enable) {
    select(view, select);
    enable(view, enable);
  }

  private void select(ToggleTool view, boolean select) {
    if (select) {
      view.select();
    } else {
      view.deselect();
    }
  }

  private void enable(ToggleTool view, boolean enable) {
    if (enable) {
      view.enable();
    } else {
      view.disable();
    }
  }
}