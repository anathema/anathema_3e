package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.hero.experience.model.ExperienceChange;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.specialties.model.ISpecialtyListener;
import net.sf.anathema.hero.specialties.model.ISubTraitContainer;
import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.hero.traits.display.TraitTypeInternationalizer;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.collection.IdentityMapping;
import net.sf.anathema.library.fx.dot.ExtensibleDotView;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.taskbar.BasicUi;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class SpecialtiesConfigurationPresenter {

  private final IdentityMapping<Specialty, ExtensibleDotView> viewsBySpecialty = new IdentityMapping<>();
  private final IdentityMapping<Specialty, Tool> deleteToolsBySpecialty = new IdentityMapping<>();
  private final TraitTypeInternationalizer i18ner;
  private final Comparator<TraitType> comparator;

  private final ISpecialtyListener specialtyListener = new ISpecialtyListener() {
    @Override
    public void subTraitAdded(Specialty specialty) {
      addSpecialtyView(specialty);
    }

    @Override
    public void subTraitRemoved(Specialty specialty) {
      ExtensibleDotView view = viewsBySpecialty.get(specialty);
      viewsBySpecialty.remove(specialty);
      view.remove();
    }

    @Override
    public void subTraitValueChanged() {
      // Nothing to do
    }
  };

  private final SpecialtiesConfigurationView configurationView;
  private Hero hero;
  private final SpecialtiesModel specialtyManagement;

  public SpecialtiesConfigurationPresenter(Hero hero, SpecialtiesModel specialtyManagement,
                                           SpecialtiesConfigurationView configurationView, Resources resources) {
    this.hero = hero;
    this.specialtyManagement = specialtyManagement;
    this.configurationView = configurationView;
    this.i18ner = new TraitTypeInternationalizer(resources);
    this.comparator = new TraitTypeByNameComparator(i18ner);
  }

  public void initPresentation() {
    initTraitListening();
    RelativePath addIcon = new BasicUi().getAddIconPath();
    AgnosticUIConfiguration<TraitType> configuration = new TraitTypeUiConfiguration(i18ner);
    final SpecialtyCreationView creationView = configurationView.addSpecialtyCreationView(configuration, addIcon);
    setObjects(creationView);
    creationView.addSelectionChangedListener(specialtyManagement::setCurrentTrait);
    creationView.addEditChangedListener(specialtyManagement::setCurrentSpecialtyName);
    creationView.whenAddButtonIsClicked(() -> {
      specialtyManagement.commitSelection();
      resetAndSyncView(creationView);
    });
    specialtyManagement.addSelectionChangeListener(() -> creationView.setButtonEnabled(specialtyManagement.isEntryComplete()));
    for (TraitType reference : getAllTraits()) {
      for (Specialty specialty : getSpecialtyContainer(reference).getSubTraits()) {
        addSpecialtyView(specialty);
      }
    }
    hero.getChangeAnnouncer().addListener(flavor -> {
      if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
        updateSpecialtyViewButtons();
      }
      setObjects(creationView);
    });
    resetAndSyncView(creationView);
    updateSpecialtyViewButtons();
  }

  private void resetAndSyncView(SpecialtyCreationView creationView) {
    reset();
    sync(creationView);
  }

  private void reset() {
    specialtyManagement.clear();
    specialtyManagement.setCurrentTrait(getSortedEligibleTraits().get(0));
  }

  private void sync(SpecialtyCreationView creationView) {
    creationView.selectTrait(specialtyManagement.getCurrentTrait());
    creationView.enterName(specialtyManagement.getCurrentName());
  }

  private void setObjects(SpecialtyCreationView specialtySelectionView) {
    Collection<TraitType> allTraits = getSortedEligibleTraits();
    specialtySelectionView.setObjects(allTraits);
  }

  private List<TraitType> getSortedEligibleTraits() {
    List<TraitType> allTraits = getAllEligibleTraits();
    allTraits.sort(comparator);
    return allTraits;
  }

  private void initTraitListening() {
    for (TraitType reference : getAllTraits()) {
      getSpecialtyContainer(reference).addSubTraitListener(specialtyListener);
    }
  }

  private Iterable<TraitType> getAllTraits() {
    return specialtyManagement.getAllParentTraits();
  }

  private List<TraitType> getAllEligibleTraits() {
    return specialtyManagement.getAllEligibleParentTraits();
  }

  private void updateSpecialtyViewButtons() {
    for (TraitType trait : getAllTraits()) {
      for (Specialty specialty : getSpecialtyContainer(trait).getSubTraits()) {
        Tool tool = deleteToolsBySpecialty.get(specialty);
        if (specialty.getCreationValue() == 0 || !specialtyManagement.isExperienced()) {
          tool.enable();
        } else {
          tool.disable();
        }
      }
    }
  }

  private void addSpecialtyView(final Specialty specialty) {
    final TraitType type = specialty.getBasicTraitType();
    String traitName = i18ner.getScreenName(type);
    String specialtyName = specialty.getName();
    RelativePath deleteIcon = new BasicUi().getRemoveIconPath();
    final ExtensibleDotView specialtyView = configurationView.addSpecialtyView(traitName, specialtyName, deleteIcon,
            specialty.getCurrentValue(), specialty.getMaximalValue());
    new TraitPresenter(specialty, specialtyView.getIntValueView()).initPresentation();
    Tool deleteTool = specialtyView.addToolBehind();
    deleteTool.setIcon(deleteIcon);
    deleteTool.setCommand(() -> getSpecialtyContainer(type).removeSubTrait(specialty));
    viewsBySpecialty.put(specialty, specialtyView);
    deleteToolsBySpecialty.put(specialty, deleteTool);
  }

  private ISubTraitContainer getSpecialtyContainer(TraitType type) {
    return specialtyManagement.getSpecialtiesContainer(type);
  }

}