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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static net.sf.anathema.hero.traits.model.state.CasteTraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.DefaultTraitStateType.Default;
import static net.sf.anathema.hero.traits.model.state.FavoredTraitStateType.Favored;
import static net.sf.anathema.hero.traits.model.state.SupernalTraitStateType.Supernal;

public class FavoredIconSelector {
  private final Tool tool;
  private final Map<TraitStateType, Function<Hero, RelativePath>> typeToPath = new HashMap<>();

  public FavoredIconSelector(Tool tool, PresentationProperties presentationProperties) {
    this.tool = tool;
    typeToPath.put(Default, hero -> AgnosticUIConfiguration.NO_ICON);
    typeToPath.put(Supernal, hero -> {
      HeroType heroType = hero.getSplat().getTemplateType().getHeroType();
      // TODO: Need a proper icon here
      return new HeroUI().getLinkIconPath();
    });
    typeToPath.put(Caste, hero -> {
      CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
      return new CasteUI(presentationProperties).getSmallCasteIconPath(casteType);
    });
    typeToPath.put(Favored, hero -> {
      HeroType heroType = hero.getSplat().getTemplateType().getHeroType();
      return new HeroUI().getMediumBallPath(heroType);
    });
  }

  public void setIconFor(Hero hero, TraitStateType state) {
    RelativePath path = determineIconPath(hero, state);
    tool.setIcon(path);
  }

  private RelativePath determineIconPath(Hero hero, TraitStateType state) {
    return typeToPath.get(state).apply(hero);
  }
}