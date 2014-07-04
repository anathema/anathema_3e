package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.SubViewMap;
import net.sf.anathema.hero.application.SubViewRegistry;
import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.application.presenter.HeroPresenter;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.Stylesheet;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

import java.util.ArrayList;
import java.util.List;

public class CharacterViewFactory {
  private final Environment environment;
  private final ApplicationModel applicationModel;

  public CharacterViewFactory(Environment environment, ApplicationModel applicationModel) {
    this.environment = environment;
    this.applicationModel = applicationModel;
  }

  public NodeHolder createView(HeroItemData hero) {
    SubViewRegistry viewFactory = new SubViewMap(environment.getObjectFactory());
    Stylesheet[] stylesheets = createStylesheets(hero);
    TaskedHeroView characterView = new TaskedHeroView(viewFactory, stylesheets);
    new HeroPresenter(hero, characterView, environment, applicationModel).initPresentation();
    hero.getChangeManagement().setClean();
    return characterView;
  }

  private Stylesheet[] createStylesheets(Hero hero) {
    String[] skins = new CssSkinner().getSkins(hero.getSplat().getTemplateType().getCharacterType());
    List<Stylesheet> stylesheets = new ArrayList<>();
    for (String skin : skins) {
      stylesheets.add(new Stylesheet(skin));
    }
    return stylesheets.toArray(new Stylesheet[stylesheets.size()]);
  }
}