package net.sf.anathema.hero.display.fx.perspective.content.layout;

import java.util.HashMap;
import java.util.Map;

public class RasterLayoutMap {

  private Map<String, RasterLayout> layoutMap = new HashMap<>();
  private RasterLayoutImpl defaultLayout = new RasterLayoutImpl();

  public RasterLayout get(String key) {
    return layoutMap.getOrDefault(key, defaultLayout);
  }

  public void setLayout(String key, RasterLayout layout) {
    layoutMap.put(key, layout);
  }
}
