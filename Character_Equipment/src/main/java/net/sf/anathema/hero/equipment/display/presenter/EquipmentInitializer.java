package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.overview.HeroModelGroup;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModelImpl;

@RegisteredInitializer(HeroModelGroup.Miscellaneous)
@Weight(weight = 200)
public class EquipmentInitializer implements HeroModelInitializer {
  @SuppressWarnings("UnusedParameters")
  public EquipmentInitializer(ApplicationModelImpl model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String viewName = environment.getString("AdditionalTemplateView.TabName.Equipment");
    EquipmentView view = sectionView.addView(viewName, EquipmentView.class);
    EquipmentModel equipmentModel = EquipmentModelFetcher.fetch(hero);
    new EquipmentPresenter(environment, equipmentModel, view).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return EquipmentModelFetcher.fetch(hero) != null;
  }
}
