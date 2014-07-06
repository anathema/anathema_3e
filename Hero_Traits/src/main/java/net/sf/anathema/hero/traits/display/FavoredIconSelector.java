package net.sf.anathema.hero.traits.display;

import net.sf.anathema.hero.concept.display.caste.presenter.CasteUI;
import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.environment.herotype.PresentationProperties;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.hero.individual.view.HeroUI;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;

public class FavoredIconSelector {
  private final Tool tool;
  private final PresentationProperties presentationProperties;

  public FavoredIconSelector(Tool tool, PresentationProperties presentationProperties) {
    this.tool = tool;
    this.presentationProperties = presentationProperties;
  }


  public void setIconFor(Hero hero, TraitStateType state) {
    RelativePath path = determineIconPath(hero, state);
    tool.setIcon(path);
  }

  private RelativePath determineIconPath(Hero hero, TraitStateType state) {
	  if (state == TraitStateType.Supernal) {
		  HeroType heroType = hero.getSplat().getTemplateType().getHeroType();
		  // TODO: Need a proper icon here
		  return new HeroUI().getLinkIconPath();
	  }
	  if (state == TraitStateType.Caste) {
		  CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
		  return new CasteUI(presentationProperties).getSmallCasteIconPath(casteType);
	  }
	  if (state == TraitStateType.Favored) {
		  HeroType heroType = hero.getSplat().getTemplateType().getHeroType();
		  return new HeroUI().getMediumBallPath(heroType);
	  }
	  return AgnosticUIConfiguration.NO_ICON;
  }
}