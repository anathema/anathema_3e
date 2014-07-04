package net.sf.anathema.points.display.overview.view;

import net.sf.anathema.hero.framework.library.overview.OverviewCategory;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class DefaultCategorizedOverview implements CategorizedOverview {

  private final MigPane panel = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));

  @Override
  public final OverviewCategory addOverviewCategory(String borderText) {
    return new FxOverviewCategory(panel, borderText);
  }

  @Override
  public void showIn(OverviewDisplay display) {
    if (!(display instanceof FxOverviewDisplay)) {
      return;
    }
    ((FxOverviewDisplay) display).setOverviewPane(panel);
  }
}