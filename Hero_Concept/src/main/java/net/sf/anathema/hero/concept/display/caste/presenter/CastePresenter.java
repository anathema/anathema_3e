package net.sf.anathema.hero.concept.display.caste.presenter;

import net.sf.anathema.hero.application.creation.GenericPresentationTemplate;
import net.sf.anathema.hero.elsewhere.concept.CasteSelection;
import net.sf.anathema.hero.elsewhere.concept.CasteType;
import net.sf.anathema.hero.elsewhere.concept.HeroConceptFetcher;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.ObjectSelectionView;

public class CastePresenter {

  private final CasteView view;
  private final Hero hero;
  private final Resources resources;

  public CastePresenter(Hero hero, CasteView view, Resources resources) {
    this.hero = hero;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    HeroSplat template = hero.getSplat();
    GenericPresentationTemplate presentationTemplate = new GenericPresentationTemplate(template);
    String casteLabelResourceKey = presentationTemplate.getCasteLabelResource();
    CasteType[] casteTypes = HeroConceptFetcher.fetch(hero).getCasteCollection().getAllCasteTypes(hero.getSplat().getTemplateType());
    AgnosticUIConfiguration<CasteType> casteUi = new AgnosticCasteUi(resources, presentationTemplate);
    final ObjectSelectionView<CasteType> casteView = view.addObjectSelectionView(resources.getString(casteLabelResourceKey), casteUi);
    casteView.setObjects(casteTypes);
    final CasteSelection caste = HeroConceptFetcher.fetch(hero).getCaste();
    if (caste.isNotSelected()) {
      caste.setType(casteTypes[0]);
    }
    casteView.setSelectedObject(caste.getType());
    casteView.addObjectSelectionChangedListener(caste::setType);
    caste.addChangeListener(() -> casteView.setSelectedObject(caste.getType()));
    initExperienceListening(casteView);
  }

  private void initExperienceListening(final ObjectSelectionView<CasteType> casteView) {
    final ExperienceModel experienceModel = ExperienceModelFetcher.fetch(hero);
    experienceModel.addStateChangeListener(() -> casteView.setEnabled(!experienceModel.isExperienced()));
    casteView.setEnabled(!experienceModel.isExperienced());
  }
}