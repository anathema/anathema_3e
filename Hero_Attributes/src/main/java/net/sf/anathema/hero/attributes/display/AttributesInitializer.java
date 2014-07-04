package net.sf.anathema.hero.attributes.display;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.attributes.model.AttributesModelFetcher;
import net.sf.anathema.hero.display.fx.traitview.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.framework.display.SectionView;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;

import static net.sf.anathema.hero.display.HeroModelGroup.NaturalTraits;

@RegisteredInitializer(NaturalTraits)
@Weight(weight = 0)
public class AttributesInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public AttributesInitializer(IApplicationModel applicationModel) {
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