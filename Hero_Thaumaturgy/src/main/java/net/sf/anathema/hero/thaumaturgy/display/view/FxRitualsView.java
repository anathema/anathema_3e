package net.sf.anathema.hero.thaumaturgy.display.view;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;
import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.thaumaturgy.display.presenter.RitualEntryView;
import net.sf.anathema.hero.thaumaturgy.display.presenter.RitualItemView;
import net.sf.anathema.hero.thaumaturgy.display.presenter.RitualsView;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyRitual;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.dot.FxDotView;
import net.sf.anathema.library.fx.dot.SimpleDotViewPanel;
import net.sf.anathema.library.resources.RelativePath;

import org.tbee.javafx.scene.layout.MigPane;

public class FxRitualsView implements RitualsView, NodeHolder {
  private final MigPane content = new MigPane(fillWithoutInsets());
  private final MigPane creationPane = new MigPane(withoutInsets());
  private final SimpleDotViewPanel entryPanel = new SimpleDotViewPanel();

  public FxRitualsView() {
    MigPane mainPanel = new MigPane(fillWithoutInsets().wrapAfter(1));
    mainPanel.add(creationPane, new CC().growX());
    mainPanel.add(entryPanel.getNode(), new CC().alignY("top").growX());
    content.add(mainPanel, new CC().alignY("top").growX());
  }

  @Override
  public Node getNode() {
    return content;
  }

  @Override
  public RitualEntryView addSelectionView() {
    FxRitualsEntryView view = new FxRitualsEntryView();
    creationPane.add(view.getNode());
    return view;
  }

  @Override
  public RitualItemView addMerit(String label, RelativePath removeIcon) {
    FxDotView view = FxDotView.WithDefaultLayout(label, ThaumaturgyRitual.RITUAL_MAX_LEVEL);
    FxRitualsItemView itemView = new FxRitualsItemView(view);
    itemView.addTo(entryPanel);
    return itemView;
  }
}