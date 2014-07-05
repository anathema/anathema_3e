package net.sf.anathema.hero.traits.display;

import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.environment.herotype.PresentationPropertiesImpl;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.GroupedTraitsModel;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.library.collection.IdentityMapping;
import net.sf.anathema.library.fx.dot.ExtensibleDotView;
import net.sf.anathema.library.fx.dot.GroupedStatedDotsView;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.Style;

import java.util.List;

import static net.sf.anathema.hero.experience.model.ExperienceChange.FLAVOR_EXPERIENCE_STATE;
import static net.sf.anathema.hero.traits.model.state.TraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.TraitStateType.Favored;

public class StatedTraitModelPresenter {

  public static final Style STATE_SELECTION_BUTTON = new Style("caste-button");
  public static final Style POSSIBLE_CASTE_BUTTON = new Style("possible-caste-button");
  private final GroupedStatedDotsView view;
  private final IdentityMapping<Trait, ToggleTool> casteToggleByTrait = new IdentityMapping<>();
  private final Resources resources;
  private Hero hero;
  private GroupedTraitsModel model;

  public StatedTraitModelPresenter(Hero hero, GroupedTraitsModel model, GroupedStatedDotsView view, Resources resources) {
    this.model = model;
    this.hero = hero;
    this.resources = resources;
    this.view = view;
  }

  public void init(String typePrefix) {
    for (IdentifiedTraitTypeList traitTypeGroup : model.getGroups()) {
      view.startNewTraitGroup(resources.getString(typePrefix + "." + traitTypeGroup.getListId().getId()));
      List<TraitType> allTraitTypes = traitTypeGroup.getAll();
      addTraitViews(model.getTraits(allTraitTypes.toArray(new TraitType[allTraitTypes.size()])));
    }
    hero.getChangeAnnouncer().addListener(flavor -> {
      if (FLAVOR_EXPERIENCE_STATE.equals(flavor)) {
        updateButtons();
      }
    });
    HeroConceptFetcher.fetch(hero).getCaste().addChangeListener(() -> updateButtons());
    updateButtons();
  }

  private void updateButtons() {
    for (Trait trait : getAllTraits()) {
      ToggleTool view = casteToggleByTrait.get(trait);
      boolean disabled = ExperienceModelFetcher.fetch(hero).isExperienced();
      boolean favored = model.getState(trait).isCasteOrFavored();
      setButtonState(view, favored, !disabled);
      Style style = model.getState(trait).isSelectableForCaste() ? POSSIBLE_CASTE_BUTTON : STATE_SELECTION_BUTTON;
      view.setStyle(style);
    }
  }

  private Trait[] getAllTraits() {
    return model.getAll();
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

  private void addCasteAndFavoredToggle(Trait trait, ExtensibleDotView traitView) {
    ToggleTool casteTool = traitView.addToggleInFront();
    casteTool.setStyle(STATE_SELECTION_BUTTON);
    TraitState traitState = model.getState(trait);
    casteTool.setCommand(traitState::advanceState);
    traitState.addTraitStateChangedListener(state -> updateView(casteTool, state));
    updateView(casteTool, traitState.getType());
    casteToggleByTrait.put(trait, casteTool);
  }

  private void updateView(final ToggleTool view, TraitStateType state) {
    boolean select = state == Favored || state == Caste;
    setButtonState(view, select, true);
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