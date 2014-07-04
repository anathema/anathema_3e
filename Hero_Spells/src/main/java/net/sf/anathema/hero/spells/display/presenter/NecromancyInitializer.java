package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.framework.display.SectionView;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.spells.model.CircleModel;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

import static net.sf.anathema.hero.display.HeroModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 300)
public class NecromancyInitializer implements HeroModelInitializer {

  private ApplicationModel applicationModel;

  public NecromancyInitializer(ApplicationModel applicationModel) {
    this.applicationModel = applicationModel;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    SpellsModel spellsModel = SpellsModelFetcher.fetch(hero);
    boolean canLeanNecromancy = spellsModel.canLearnNecromancy();
    if (canLeanNecromancy) {
      String titleKey = "CardView.CharmConfiguration.Necromancy.Title";
      CircleModel circleModel = new CircleModel(spellsModel.getNecromancyCircles());
      new SpellInitializer(applicationModel, titleKey, circleModel).initialize(sectionView, hero, environment);
    }
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return SpellsModelFetcher.fetch(hero) != null;
  }
}
