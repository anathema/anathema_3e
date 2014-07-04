package net.sf.anathema.library.fx.view;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import net.sf.anathema.library.fx.Stylesheet;

public class StyledTitledPane {

  public static Node Create(String titleString, Node content) {
    TitledPane titledPane = new TitledPane(titleString, content);
    titledPane.setCollapsible(false);
    titledPane.getStyleClass().add("titledsection");
    new Stylesheet("skin/platform/styledtitledpane.css").applyToParent(titledPane);
    return titledPane;
  }
}