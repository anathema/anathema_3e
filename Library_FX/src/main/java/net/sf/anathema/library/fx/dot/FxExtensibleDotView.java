package net.sf.anathema.library.fx.dot;

import net.miginfocom.layout.CC;
import net.sf.anathema.library.fx.tool.FxButtonTool;
import net.sf.anathema.library.fx.tool.FxToggleTool;
import net.sf.anathema.library.fx.view.FxComponent;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.view.IntValueView;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;

public class FxExtensibleDotView implements ExtensibleDotView {
  private final MigPane front = new MigPane(fillWithoutInsets());
  private final MigPane center = new MigPane(fillWithoutInsets().wrapAfter(2));
  private final MigPane rear = new MigPane(fillWithoutInsets());
  private FxDotView view;
  private DotViewPanel parent;

  public FxExtensibleDotView(FxDotView view) {
    this.view = view;
    view.addTo(center);
  }

  @Override
  public IntValueView getIntValueView() {
    return view;
  }

  @Override
  public ToggleTool addToggleInFront() {
    FxToggleTool toggleTool = FxToggleTool.create();
    toggleTool.setStyleClass("castebutton");
    addToPanel(front, toggleTool);
    return toggleTool;
  }

  @Override
  public ToggleTool addToggleBehind() {
    FxToggleTool toggleTool = FxToggleTool.create();
    addToPanel(rear, toggleTool);
    return toggleTool;
  }

  @Override
  public Tool addToolBehind() {
    FxButtonTool buttonTool = FxButtonTool.ForToolbar();
    addToPanel(rear, buttonTool);
    return buttonTool;
  }

  @Override
  public void remove() {
    removePart(front);
    removePart(center);
    removePart(rear);
  }

  @SuppressWarnings("UnusedParameters")
  private void removePart(MigPane panel) {
    parent.remove(panel);
  }

  public void addTo(DotViewPanel panel) {
    this.parent = panel;
    panel.add(front, new CC().alignY("center"));
    panel.add(center, new CC().growX().pushX().alignY("center"));
    panel.add(rear, new CC().alignY("center"));
  }

  private void addToPanel(MigPane pane, FxComponent toggleTool) {
    pane.add(toggleTool.getNode(), new CC().alignY("center"));
  }
}