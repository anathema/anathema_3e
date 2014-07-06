package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.SubViewMap;
import net.sf.anathema.hero.application.SubViewRegistry;
import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.application.presenter.HeroPresenter;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.Stylesheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CharacterViewFactory {
  private final HeroEnvironment environment;

  public CharacterViewFactory(HeroEnvironment environment) {
    this.environment = environment;
  }

  public NodeHolder createView(HeroItemData hero) {
    SubViewRegistry viewFactory = new SubViewMap(environment.getObjectFactory());
    Collection<Stylesheet> stylesheets = createStylesheets(hero);
    TaskedHeroView characterView = new TaskedHeroView(viewFactory, stylesheets);
    new HeroPresenter(hero, characterView, environment).initPresentation();
    hero.getChangeManagement().setClean();
    return characterView;
  }

  private Collection<Stylesheet> createStylesheets(Hero hero) {
    Collection<String> skins = new CssSkinner().getSkins(hero.getSplat().getTemplateType().getHeroType());
    return skins.stream().map(Stylesheet::new).collect(Collectors.toList());
  }
}