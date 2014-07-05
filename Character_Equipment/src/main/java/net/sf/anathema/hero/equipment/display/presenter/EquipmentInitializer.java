package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.initialization.Weight;

@RegisteredInitializer(HeroModelGroup.Miscellaneous)
@Weight(weight = 200)
public class EquipmentInitializer implements HeroModelInitializer {
  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public EquipmentInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String viewName = environment.getResources().getString("AdditionalTemplateView.TabName.Equipment");
    EquipmentView view = sectionView.addView(viewName, EquipmentView.class);
    EquipmentModel equipmentModel = EquipmentModelFetcher.fetch(hero);
    new EquipmentPresenter(environment.getResources(), equipmentModel, view).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return EquipmentModelFetcher.fetch(hero) != null;
  }
}
