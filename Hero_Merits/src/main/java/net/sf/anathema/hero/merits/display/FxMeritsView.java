package net.sf.anathema.hero.merits.display;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.dot.FxDotView;
import net.sf.anathema.library.fx.dot.SimpleDotViewPanel;
import net.sf.anathema.library.resources.RelativePath;

import org.tbee.javafx.scene.layout.MigPane;

public class FxMeritsView implements MeritsView, NodeHolder {
  private final MigPane content = new MigPane(fillWithoutInsets());
  private final MigPane creationPane = new MigPane(withoutInsets());
  private final SimpleDotViewPanel entryPanel = new SimpleDotViewPanel();

  public FxMeritsView() {
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
  public MeritEntryView addSelectionView() {
    FxMeritsEntryView view = new FxMeritsEntryView();
    creationPane.add(view.getNode());
    return view;
  }

  @Override
  public MeritItemView addMerit(String label, RelativePath removeIcon) {
    FxDotView view = FxDotView.WithDefaultLayout(label, MeritOption.MAX_MERIT_RATING);
    FxMeritItemView itemView = new FxMeritItemView(view);
    itemView.addTo(entryPanel);
    return itemView;
  }
}