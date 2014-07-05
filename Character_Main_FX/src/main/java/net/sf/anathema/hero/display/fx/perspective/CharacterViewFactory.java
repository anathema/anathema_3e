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
import java.util.List;

public class CharacterViewFactory {
  private final HeroEnvironment environment;

  public CharacterViewFactory(HeroEnvironment environment) {
    this.environment = environment;
  }

  public NodeHolder createView(HeroItemData hero) {
    SubViewRegistry viewFactory = new SubViewMap(environment.getObjectFactory());
    Stylesheet[] stylesheets = createStylesheets(hero);
    TaskedHeroView characterView = new TaskedHeroView(viewFactory, stylesheets);
    new HeroPresenter(hero, characterView, environment).initPresentation();
    hero.getChangeManagement().setClean();
    return characterView;
  }

  private Stylesheet[] createStylesheets(Hero hero) {
    String[] skins = new CssSkinner().getSkins(hero.getSplat().getTemplateType().getHeroType());
    List<Stylesheet> stylesheets = new ArrayList<>();
    for (String skin : skins) {
      stylesheets.add(new Stylesheet(skin));
    }
    return stylesheets.toArray(new Stylesheet[stylesheets.size()]);
  }
}