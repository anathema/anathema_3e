package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.spells.model.CircleModel;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.library.initialization.Weight;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 300)
public class NecromancyInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  public NecromancyInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    SpellsModel spellsModel = SpellsModelFetcher.fetch(hero);
    boolean canLeanNecromancy = spellsModel.canLearnNecromancy();
    if (canLeanNecromancy) {
      String titleKey = "CardView.CharmConfiguration.Necromancy.Title";
      CircleModel circleModel = new CircleModel(spellsModel.getNecromancyCircles());
      new SpellInitializer(environment, titleKey, circleModel).initialize(sectionView, hero);
    }
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return SpellsModelFetcher.fetch(hero) != null;
  }
}
