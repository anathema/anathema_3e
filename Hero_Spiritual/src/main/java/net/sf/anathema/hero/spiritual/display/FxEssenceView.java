package net.sf.anathema.hero.spiritual.display;

import javafx.scene.Node;
import net.sf.anathema.library.fx.dot.FxDotView;
import net.sf.anathema.library.view.IntValueView;
import net.sf.anathema.library.view.StyledValueView;
import net.sf.anathema.points.display.overview.view.FxStringOverview;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;

public class FxEssenceView {
  private final MigPane panel = new MigPane(fillWithoutInsets().wrapAfter(2));

  public Node getNode() {
    return panel;
  }

  public StyledValueView<String> addPoolView(String labelText) {
    FxStringOverview poolView = new FxStringOverview(labelText);
    poolView.addTo(panel);
    return poolView;
  }

  public IntValueView addEssenceView(String labelText, int maxValue) {
    FxDotView essenceView = FxDotView.WithDefaultLayout(labelText, maxValue);
    essenceView.addTo(panel);
    return essenceView;
  }


}
