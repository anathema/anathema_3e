package net.sf.anathema.points.display.overview.view;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.individual.overview.OverviewCategory;
import net.sf.anathema.library.fx.view.StyledTitledPane;
import net.sf.anathema.library.view.LabelledAllotmentView;
import net.sf.anathema.library.view.StyledValueView;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxOverviewCategory implements OverviewCategory {
  private final MigPane panel = new MigPane(withoutInsets().wrapAfter(4));
  private Node overViewCategory;

  public FxOverviewCategory(final MigPane parent, final String label) {
    overViewCategory = StyledTitledPane.Create(label, panel);
    parent.add(overViewCategory, new CC().grow());
  }

  @Override
  public LabelledAllotmentView addAllotmentView(String labelText, int maxValueLength) {
    FxAllotmentOverview view = new FxAllotmentOverview(labelText);
    view.addTo(panel);
    return view;
  }

  @Override
  public StyledValueView<Integer> addIntegerValueView(String labelText, int maxValueLength) {
    FxIntegerOverview view = new FxIntegerOverview(labelText);
    view.addTo(panel);
    return view;
  }

  @Override
  public StyledValueView<String> addStringValueView(String labelText) {
    FxStringOverview view = new FxStringOverview(labelText);
    view.addTo(panel);
    return view;
  }

  public Node getNode() {
    return overViewCategory;
  }
}