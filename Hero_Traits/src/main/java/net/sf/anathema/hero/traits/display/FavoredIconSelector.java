package net.sf.anathema.hero.traits.display;

import net.sf.anathema.hero.application.CharacterUI;
import net.sf.anathema.hero.concept.display.caste.presenter.CasteUI;
import net.sf.anathema.hero.elsewhere.concept.CasteType;
import net.sf.anathema.hero.elsewhere.concept.HeroConceptFetcher;
import net.sf.anathema.hero.environment.herotype.PresentationProperties;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.hero.traits.model.FavorableState;
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


  public void setIconFor(Hero hero, FavorableState state) {
    RelativePath path = determineIconPath(hero, state);
    tool.setIcon(path);
  }

  private RelativePath determineIconPath(Hero hero, FavorableState state) {
	  if (state == FavorableState.Supernal) {
		  CharacterType characterType = hero.getSplat().getTemplateType().getCharacterType();
		  // TODO: Need a proper icon here
		  return new CharacterUI().getLinkIconPath();
	  }
	  if (state == FavorableState.Caste) {
		  CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
		  return new CasteUI(presentationProperties).getSmallCasteIconPath(casteType);
	  }
	  if (state == FavorableState.Favored) {
		  CharacterType characterType = hero.getSplat().getTemplateType().getCharacterType();
		  return new CharacterUI().getMediumBallPath(characterType);
	  }
	  return AgnosticUIConfiguration.NO_ICON;
  }
}