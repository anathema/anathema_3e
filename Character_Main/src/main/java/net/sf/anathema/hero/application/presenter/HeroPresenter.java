package net.sf.anathema.hero.application.presenter;

import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.framework.display.HeroView;
import net.sf.anathema.hero.framework.display.SectionView;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

import static net.sf.anathema.hero.display.HeroModelGroup.Magic;
import static net.sf.anathema.hero.display.HeroModelGroup.Miscellaneous;
import static net.sf.anathema.hero.display.HeroModelGroup.NaturalTraits;
import static net.sf.anathema.hero.display.HeroModelGroup.Outline;
import static net.sf.anathema.hero.display.HeroModelGroup.SpiritualTraits;

public class HeroPresenter {

  private final InitializerList initializerList;
  private final Hero hero;
  private final HeroView heroView;
  private final Environment environment;

  public HeroPresenter(Hero hero, HeroView view, Environment environment, ApplicationModel model) {
    this.initializerList = new InitializerList(environment, model);
    this.hero = hero;
    this.heroView = view;
    this.environment = environment;
  }

  public void initPresentation() {
    initializeSection("CardView.Outline.Title", Outline);
    initializeSection("CardView.NaturalTraits.Title", NaturalTraits);
    initializeSection("CardView.SpiritualTraits.Title", SpiritualTraits);
    initializeSection("CardView.CharmConfiguration.Title", Magic);
    initializeSection("CardView.MiscellaneousConfiguration.Title", Miscellaneous);
  }

  private void initializeSection(String titleKey, HeroModelGroup group) {
    SectionView sectionView = prepareSection(titleKey);
    initializeGroup(group, sectionView);
    sectionView.finishInitialization();
  }

  private SectionView prepareSection(String titleKey) {
    String sectionTitle = environment.getString(titleKey);
    return heroView.addSection(sectionTitle);
  }

  private void initializeGroup(HeroModelGroup group, SectionView sectionView) {
    for (HeroModelInitializer initializer : initializerList.getInOrderFor(group)) {
      if (initializer.canWorkForHero(hero)) {
        initializer.initialize(sectionView, hero, environment);
      }
    }
  }
}