package net.sf.anathema.hero.charms.display;

import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.display.coloring.CharmBorderColorEvaluator;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.magic.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.charms.display.tree.CharacterCharmTreePresenter;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.Charms;

@RegisteredInitializer(Charms)
@Weight(weight = 0)
public class CharmInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  public CharmInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    MagicDescriptionProvider provider = CharmDescriptionProviderExtractor.CreateFor(environment);
    CharmBorderColorEvaluator evaluator = new CharmBorderColorEvaluator(environment.getObjectFactory());
    CharmDisplayModel model = new CharmDisplayModel(hero, evaluator, provider);
    HeroType heroType = hero.getSplat().getTemplateType().getHeroType();
    CharmDisplayPropertiesMap propertiesMap = new CharmDisplayPropertiesMap(environment.getObjectFactory());
    TreePresentationProperties presentationProperties = propertiesMap.getDisplayProperties(heroType);
    String header = environment.getResources().getString("CardView.CharmConfiguration.CharmSelection.Title");
    CharmView charmView = sectionView.addView(header, CharmView.class);
    CharmMap charmCache = getCharmIdMap();
    CharacterCharmTreePresenter treePresenter = new CharacterCharmTreePresenter(environment.getResources(), charmView,
      model, presentationProperties, charmCache, provider);
    treePresenter.initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return CharmsModelFetcher.fetch(hero) != null;
  }

  private CharmMap getCharmIdMap() {
    return environment.getDataSet(CharmCache.class);
  }
}