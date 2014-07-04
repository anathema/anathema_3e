package net.sf.anathema.hero.display.fx.configurableview;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.display.fx.traitview.FxTraitView;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.text.FxTextView;
import net.sf.anathema.library.fx.tool.FxButtonTool;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.ConfigurableCharacterView;
import net.sf.anathema.library.view.IntValueView;
import net.sf.anathema.library.view.MultiComponentLine;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxConfigurableView implements ConfigurableCharacterView, NodeHolder {

  private final MigPane pane = new MigPane(withoutInsets().wrapAfter(2));
  private final List<MigPane> buttonPanels = new ArrayList<>();

  @Override
  public MultiComponentLine addMultiComponentLine() {
    FxMultiComponentLine line = new FxMultiComponentLine();
    pane.add(line.getNode(), new CC().spanX().wrap());
    return line;
  }

  @Override
  public ITextView addLineView(String labelText) {
    FxTextView view = FxTextView.SingleLine(labelText);
    addTextView(view);
    return view;
  }

  @Override
  public ITextView addAreaView(String labelText) {
    FxTextView view = FxTextView.MultiLine(labelText, 4);
    addTextView(view);
    return view;
  }

  @Override
  public Tool addEditAction() {
    FxButtonTool interaction = FxButtonTool.ForToolbar();
    MigPane mostRecentPanel = buttonPanels.get(buttonPanels.size() - 1);
    mostRecentPanel.add(interaction.getNode());
    return interaction;
  }

  @Override
  public IntValueView addDotSelector(String label, int maxValue) {
    FxTraitView view = FxTraitView.AsSingleNode(label, maxValue);
    view.addTo(pane);
    return view;
  }

  private void addTextView(final FxTextView view) {
    pane.add(view.getNode(), new CC().growX());
    MigPane buttonPanel = new MigPane(withoutInsets());
    buttonPanels.add(buttonPanel);
    pane.add(buttonPanel, new CC().alignY("center"));
  }

  @Override
  public Node getNode() {
    return pane;
  }
}