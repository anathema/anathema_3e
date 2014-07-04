package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.spells.model.CircleModel;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 200)
public class SorceryInitializer implements HeroModelInitializer {
  private ApplicationModel model;

  public SorceryInitializer(ApplicationModel model) {
    this.model = model;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    SpellsModel spellsModel = SpellsModelFetcher.fetch(hero);
    boolean canLeanSorcery = spellsModel.canLearnSorcery();
    if (canLeanSorcery) {
      String titleKey = "CardView.CharmConfiguration.Spells.Title";
      CircleModel circleModel = new CircleModel(spellsModel.getSorceryCircles());
      new SpellInitializer(model, titleKey, circleModel).initialize(sectionView, hero, environment);
    }
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return SpellsModelFetcher.fetch(hero) != null;
  }
}
