package net.sf.anathema.hero.display.fx.perspective.content;

import net.sf.anathema.hero.application.SubViewMap;
import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.display.fx.perspective.CssSkinner;
import net.sf.anathema.hero.display.fx.perspective.content.layout.DefaultCell;
import net.sf.anathema.hero.display.fx.perspective.content.layout.RasterLayoutImpl;
import net.sf.anathema.hero.display.fx.perspective.content.layout.RasterLayoutMap;
import net.sf.anathema.hero.display.fx.perspective.content.layout.SpanCell;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.fx.Stylesheet;
import net.sf.anathema.library.initialization.ObjectFactory;

import java.util.Collection;
import java.util.stream.Collectors;

public class HeroViewFactory {

  private ObjectFactory objectFactory;

  public HeroViewFactory(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  public TopBarHeroView createView(HeroItemData hero) {
    SubViewMap viewFactory = new SubViewMap(objectFactory);
    Collection<Stylesheet> stylesheets = createStylesheets(hero);
    return new TopBarHeroView(viewFactory, stylesheets, createRasterLayoutMap());
  }

  private RasterLayoutMap createRasterLayoutMap() {
    RasterLayoutMap rasterLayoutMap = new RasterLayoutMap();
    rasterLayoutMap.setLayout("Spiritual", new RasterLayoutImpl(2, new SpanCell(1, 2)));
    rasterLayoutMap.setLayout("Background", new RasterLayoutImpl(2, new SpanCell(1, 2)));
    rasterLayoutMap.setLayout("Mundane", new RasterLayoutImpl(3, new DefaultCell(), new SpanCell(2, 1), new SpanCell(2, 1)));
    return rasterLayoutMap;
  }

  private Collection<Stylesheet> createStylesheets(Hero hero) {
    Collection<String> skins = new CssSkinner().getSkins(hero.getSplat().getTemplateType().getHeroType());
    return skins.stream().map(Stylesheet::new).collect(Collectors.toList());
  }
}