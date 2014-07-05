package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.spells.model.CircleModel;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.library.initialization.Weight;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 200)
public class SorceryInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  public SorceryInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    SpellsModel spellsModel = SpellsModelFetcher.fetch(hero);
    boolean canLeanSorcery = spellsModel.canLearnSorcery();
    if (canLeanSorcery) {
      String titleKey = "CardView.CharmConfiguration.Spells.Title";
      CircleModel circleModel = new CircleModel(spellsModel.getSorceryCircles());
      new SpellInitializer(environment, titleKey, circleModel).initialize(sectionView, hero);
    }
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return SpellsModelFetcher.fetch(hero) != null;
  }
}
