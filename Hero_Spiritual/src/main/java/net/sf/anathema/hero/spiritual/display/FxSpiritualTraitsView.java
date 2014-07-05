package net.sf.anathema.hero.spiritual.display;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.dot.FxDotView;
import net.sf.anathema.library.fx.view.StyledTitledPane;
import net.sf.anathema.library.view.IntValueView;
import net.sf.anathema.library.view.StyledValueView;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxSpiritualTraitsView implements SpiritualTraitsView, NodeHolder {

  private final MigPane willpowerPanel = new MigPane(fillWithoutInsets().wrapAfter(2));
  private final FxEssenceView essenceView = new FxEssenceView();
  private final MigPane content = new MigPane(withoutInsets().wrapAfter(1));

  @Override
  public void initGui(SpiritualTraitsViewProperties properties) {
    addTitledPanel(properties.getWillpowerTitle(), content, willpowerPanel, new CC().alignY("top").growX());
    addTitledPanel(properties.getEssenceTitle(), content, essenceView.getNode(), new CC().alignY("top"));
  }

  @Override
  public IntValueView addWillpower(String labelText, int maxValue) {
    FxDotView willpowerView = FxDotView.WithDefaultLayout(labelText, maxValue);
    willpowerView.addTo(willpowerPanel);
    return willpowerView;
  }

  @Override
  public IntValueView addEssenceView(String labelText, int maxValue) {
    return essenceView.addEssenceView(labelText, maxValue);
  }

  @Override
  public StyledValueView<String> addPoolView(String labelText) {
    return essenceView.addPoolView(labelText);
  }

  @Override
  public Node getNode() {
    return content;
  }

  private void addTitledPanel(String title, MigPane parent, Node content, CC constraints) {
    Node pane = StyledTitledPane.Create(title, content);
    parent.add(pane, constraints);
  }
}
