package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.combos.model.ComboConfigurationModel;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 100)
public class ComboInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  public ComboInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String header = environment.getResources().getString("CardView.CharmConfiguration.ComboCreation.Title");
    ComboConfigurationView comboView = sectionView.addView(header, ComboConfigurationView.class);
    MagicDescriptionProvider magicDescriptionProvider = CharmDescriptionProviderExtractor.CreateFor(environment);
    ComboConfigurationModel comboModel = new ComboConfigurationModel(hero, magicDescriptionProvider);
    new ComboConfigurationPresenter(hero, environment.getResources(), comboModel, comboView).initPresentation();

  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return CharmsModelFetcher.fetch(hero) != null;
  }
}
