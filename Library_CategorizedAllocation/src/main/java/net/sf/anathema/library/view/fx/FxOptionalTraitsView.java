package net.sf.anathema.library.view.fx;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;
import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.dot.FxDotView;
import net.sf.anathema.library.fx.dot.SimpleDotViewPanel;
import net.sf.anathema.library.model.property.OptionalEntryCategory;
import net.sf.anathema.library.model.trait.OptionalTraitOption;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.view.OptionalPropertyEntryView;
import net.sf.anathema.library.view.trait.OptionalTraitItemView;
import net.sf.anathema.library.view.trait.OptionalTraitsView;

import org.tbee.javafx.scene.layout.MigPane;

public class FxOptionalTraitsView<
	C extends OptionalEntryCategory,
	O extends OptionalTraitOption>
	implements OptionalTraitsView, NodeHolder {
  private final MigPane content = new MigPane(fillWithoutInsets());
  private final MigPane creationPane = new MigPane(withoutInsets());
  private final SimpleDotViewPanel entryPanel = new SimpleDotViewPanel();

  public FxOptionalTraitsView() {
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
  public OptionalPropertyEntryView addSelectionView() {
    FxOptionalTraitsEntryView view = new FxOptionalTraitsEntryView();
    creationPane.add(view.getNode());
    return view;
  }

  @Override
  public OptionalTraitItemView addItemView(String label, int maxValue, RelativePath removeIcon) {
    FxDotView view = FxDotView.WithDefaultLayout(label, maxValue);
    FxOptionalTraitItemView itemView = new FxOptionalTraitItemView(view, removeIcon);
    itemView.addTo(entryPanel);
    return itemView;
  }
}