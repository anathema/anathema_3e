package net.sf.anathema.hero.attributes.display;

import net.sf.anathema.hero.application.presenter.HeroModelInitializer;
import net.sf.anathema.hero.application.presenter.RegisteredInitializer;
import net.sf.anathema.hero.attributes.model.AttributesModelFetcher;
import net.sf.anathema.hero.display.fx.traitview.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.NaturalTraits;

@RegisteredInitializer(NaturalTraits)
@Weight(weight = 0)
public class AttributesInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public AttributesInitializer(ApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String attributeHeader = environment.getString("CardView.AttributeConfiguration.Title");
    GroupedFavorableTraitConfigurationView attributeView =
            sectionView.addView(attributeHeader, GroupedFavorableTraitConfigurationView.class);
    new AttributesPresenter(hero, environment, attributeView).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return AttributesModelFetcher.fetch(hero) != null;
  }
}