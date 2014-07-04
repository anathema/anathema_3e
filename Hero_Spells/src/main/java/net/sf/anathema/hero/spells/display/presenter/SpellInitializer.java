package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.framework.display.SectionView;
import net.sf.anathema.hero.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.model.CircleModel;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

public class SpellInitializer implements HeroModelInitializer {
  private ApplicationModel applicationModel;
  private final String titleKey;
  private final CircleModel circleModel;

  public SpellInitializer(ApplicationModel applicationModel, String titleKey, CircleModel circleModel) {
    this.applicationModel = applicationModel;
    this.titleKey = titleKey;
    this.circleModel = circleModel;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String header = environment.getString(titleKey);
    SpellView view = sectionView.addView(header, SpellView.class);
    MagicDescriptionProvider magicDescriptionProvider = CharmDescriptionProviderExtractor.CreateFor(applicationModel, environment);
    ExperienceModel experienceModel = ExperienceModelFetcher.fetch(hero);
    SpellsModel spellsModel = SpellsModelFetcher.fetch(hero);
    CharmsModel charmsModel = CharmsModelFetcher.fetch(hero);
    new SpellPresenter(circleModel, environment, view, magicDescriptionProvider, experienceModel, spellsModel,charmsModel).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return true;
  }
}