package net.sf.anathema.hero.display.fx.perspective.content;

import net.sf.anathema.hero.application.SubViewMap;
import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.display.fx.perspective.CssSkinner;
import net.sf.anathema.hero.display.fx.perspective.content.layout.ConfigurableLayout;
import net.sf.anathema.hero.display.fx.perspective.content.layout.DefaultCell;
import net.sf.anathema.hero.display.fx.perspective.content.layout.GreedyLayout;
import net.sf.anathema.hero.display.fx.perspective.content.layout.RasterLayoutMap;
import net.sf.anathema.hero.display.fx.perspective.content.layout.SpanCell;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.fx.Stylesheet;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.interaction.AcceleratorMap;

import java.util.Collection;
import java.util.stream.Collectors;

public class HeroViewFactory {

  private ObjectFactory objectFactory;
  private AcceleratorMap acceleratorMap;

  public HeroViewFactory(ObjectFactory objectFactory, AcceleratorMap acceleratorMap) {
    this.objectFactory = objectFactory;
    this.acceleratorMap = acceleratorMap;
  }

  public TopBarHeroView createView(HeroItemData hero) {
    SubViewMap viewFactory = new SubViewMap(objectFactory);
    Collection<Stylesheet> stylesheets = createStylesheets(hero);
    return new TopBarHeroView(viewFactory, stylesheets, createRasterLayoutMap(), acceleratorMap);
  }

  private RasterLayoutMap createRasterLayoutMap() {
    // todo (sandra): do not use internationalized strings as keys
    RasterLayoutMap layoutMap = new RasterLayoutMap();
    layoutMap.setLayout("Background", new ConfigurableLayout(2, new SpanCell(1, 3)));
    layoutMap.setLayout("Perks", new ConfigurableLayout(1));
    layoutMap.setLayout("Traits", new ConfigurableLayout(3, new DefaultCell(), new SpanCell(2, 1)));
    layoutMap.setLayout("Charms", new GreedyLayout());

    return layoutMap;
  }

  private Collection<Stylesheet> createStylesheets(Hero hero) {
    Collection<String> skins = new CssSkinner().getSkins(hero.getSplat().getTemplateType().getHeroType());
    return skins.stream().map(Stylesheet::new).collect(Collectors.toList());
  }

}