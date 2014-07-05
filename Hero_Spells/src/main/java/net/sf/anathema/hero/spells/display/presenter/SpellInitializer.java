package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.hero.spells.model.CircleModel;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;

public class SpellInitializer implements HeroModelInitializer {
  private HeroEnvironment environment;
  private final String titleKey;
  private final CircleModel circleModel;

  public SpellInitializer(HeroEnvironment environment, String titleKey, CircleModel circleModel) {
    this.environment = environment;
    this.titleKey = titleKey;
    this.circleModel = circleModel;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String header = environment.getResources().getString(titleKey);
    SpellView view = sectionView.addView(header, SpellView.class);
    MagicDescriptionProvider magicDescriptionProvider = CharmDescriptionProviderExtractor.CreateFor(environment);
    ExperienceModel experienceModel = ExperienceModelFetcher.fetch(hero);
    SpellsModel spellsModel = SpellsModelFetcher.fetch(hero);
    CharmsModel charmsModel = CharmsModelFetcher.fetch(hero);
    new SpellPresenter(circleModel, environment.getResources(), view, magicDescriptionProvider, experienceModel, spellsModel,charmsModel).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return true;
  }
}