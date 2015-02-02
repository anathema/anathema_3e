package net.sf.anathema.hero.display.fx.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.library.fx.view.StyledTitledPane;
import org.tbee.javafx.scene.layout.MigPane;

public class TaskedPaneMultipleContentInitializer implements MultipleContentInitializer {

  private final String header;
  private final MigPane paneContainer;

  public TaskedPaneMultipleContentInitializer(String header, MigPane paneContainer) {
    this.header = header;
    this.paneContainer = paneContainer;
  }

  @Override
  public void finishInitialization(MigPane contentPane) {
    Node styledPane = StyledTitledPane.Create(header, contentPane);
    paneContainer.add(styledPane, new CC().push().grow());
  }
}
