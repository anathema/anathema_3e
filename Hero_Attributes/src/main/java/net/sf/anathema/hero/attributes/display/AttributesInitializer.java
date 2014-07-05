package net.sf.anathema.hero.attributes.display;

import net.sf.anathema.hero.attributes.model.AttributesModelFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModelInitializer;
import net.sf.anathema.hero.individual.model.RegisteredInitializer;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.fx.dot.GroupedStatedDotsView;
import net.sf.anathema.library.initialization.Weight;

import static net.sf.anathema.hero.individual.overview.HeroModelGroup.NaturalTraits;

@RegisteredInitializer(NaturalTraits)
@Weight(weight = 0)
public class AttributesInitializer implements HeroModelInitializer {

  private HeroEnvironment environment;

  @SuppressWarnings("UnusedParameters")
  public AttributesInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero) {
    String attributeHeader = environment.getResources().getString("CardView.AttributeConfiguration.Title");
    GroupedStatedDotsView attributeView =
            sectionView.addView(attributeHeader, GroupedStatedDotsView.class);
    new AttributesPresenter(hero, environment.getResources(), attributeView).initPresentation();
  }

  @Override
  public boolean canWorkForHero(Hero hero) {
    return AttributesModelFetcher.fetch(hero) != null;
  }
}