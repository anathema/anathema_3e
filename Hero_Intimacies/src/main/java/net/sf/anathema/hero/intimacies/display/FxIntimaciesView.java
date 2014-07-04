package net.sf.anathema.hero.intimacies.display;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.individual.overview.OverviewCategory;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.view.FxRemovableStringView;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.view.RemovableEntryView;
import net.sf.anathema.points.display.overview.view.FxOverviewCategory;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxIntimaciesView implements IntimaciesView, NodeHolder {
  private final MigPane content = new MigPane(fillWithoutInsets());
  private final MigPane creationPane = new MigPane(withoutInsets());
  private final MigPane entryPanel = new MigPane(withoutInsets().wrapAfter(2));
  private final MigPane overviewPanel = new MigPane();

  public FxIntimaciesView() {
    MigPane mainPanel = new MigPane(fillWithoutInsets().wrapAfter(1));
    mainPanel.add(creationPane, new CC().growX());
    mainPanel.add(entryPanel, new CC().alignY("top").growX());
    content.add(mainPanel, new CC().alignY("top").growX());
    content.add(overviewPanel, new CC().alignY("top").growX());
  }

  @Override
  public Node getNode() {
    return content;
  }

  @Override
  public IntimacyEntryView addSelectionView(String labelText) {
    FxIntimacyEntryView view = new FxIntimacyEntryView(labelText);
    creationPane.add(view.getNode());
    return view;
  }

  @Override
  public OverviewCategory addOverview(String border) {
    return new FxOverviewCategory(overviewPanel, border);
  }

  @Override
  public void setOverview(final OverviewCategory overviewView) {
    overviewPanel.getChildren().clear();
    FxOverviewCategory view = (FxOverviewCategory) overviewView;
    overviewPanel.add(view.getNode());
  }

  @Override
  public RemovableEntryView addIntimacy(String name, RelativePath removeIcon) {
    FxRemovableStringView view = new FxRemovableStringView(removeIcon, name);
    view.addTo(entryPanel);
    return view;
  }
}