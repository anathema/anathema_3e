package net.sf.anathema.hero.specialties.display.presenter;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import net.sf.anathema.hero.experience.model.ExperienceChange;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.specialties.display.view.SpecialtyView;
import net.sf.anathema.hero.specialties.model.ISpecialtyListener;
import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.display.TraitTypeInternationalizer;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.collection.IdentityMapping;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.RemovableEntryView;
import net.sf.anathema.platform.taskbar.BasicUi;

public class SpecialtiesConfigurationPresenter {

  private final IdentityMapping<Specialty, SpecialtyView> viewsBySpecialty = new IdentityMapping<>();
  private final TraitTypeInternationalizer i18ner;
  private final Comparator<TraitType> comparator;

  private final ISpecialtyListener specialtyListener = new ISpecialtyListener() {
    @Override
    public void specialtyAdded(Specialty specialty) {
      addSpecialtyView(specialty);
    }

    @Override
    public void specialtyRemoved(Specialty specialty) {
    	SpecialtyView view = viewsBySpecialty.get(specialty);
      viewsBySpecialty.remove(specialty);
      view.delete();
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
    for (Specialty specialty : specialtyManagement.getAllSpecialties()) {
    	addSpecialtyView(specialty);
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
    specialtyManagement.setCurrentTrait(getSortedEligibleTraits().listIterator().hasNext() ? getSortedEligibleTraits().get(0) : null);
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
  	specialtyManagement.addSpecialtiesChangedListener(specialtyListener);
  }

  private List<TraitType> getAllEligibleTraits() {
    return specialtyManagement.getAllEligibleParentTraits();
  }

  private void updateSpecialtyViewButtons() {
  	for (Specialty specialty : specialtyManagement.getAllSpecialties()) {
  		SpecialtyView view = viewsBySpecialty.get(specialty);
  		if (!specialty.isLearnedAtCreation() || !specialtyManagement.isExperienced()) {
  			view.setEnabled(true);
  		} else {
  			view.setEnabled(false);
  		}
  	}
  }

  private void addSpecialtyView(final Specialty specialty) {
    final TraitType type = specialty.getBasicTraitType();
    String traitName = i18ner.getScreenName(type);
    String specialtyName = specialty.getName();
    RelativePath deleteIcon = new BasicUi().getRemoveIconPath();
    SpecialtyView specialtyView = configurationView.addSpecialtyView(traitName, specialtyName, deleteIcon);
    specialtyView.addButtonListener(() -> specialtyManagement.removeSpecialty(specialty));
    viewsBySpecialty.put(specialty, specialtyView);
  }

}