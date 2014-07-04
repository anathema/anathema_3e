package net.sf.anathema.hero.concept.display.caste.view;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.display.caste.presenter.CasteView;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxCasteView implements CasteView, NodeHolder {
  private MigPane node = new MigPane(withoutInsets().wrapAfter(2));

  @Override
  public ObjectSelectionView<CasteType> addObjectSelectionView(String labelText,
                                                               AgnosticUIConfiguration<CasteType> configuration) {
    ComboBoxSelectionView<CasteType> view = new ComboBoxSelectionView<>(labelText, configuration);
    view.setStyleClass("casteselection");
    Node viewNode = view.getNode();
    node.add(viewNode, new CC().growX().pushX());
    return view;
  }

  public Node getNode() {
    return node;
  }
}