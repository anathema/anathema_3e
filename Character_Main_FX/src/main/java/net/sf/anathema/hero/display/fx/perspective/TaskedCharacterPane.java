package net.sf.anathema.hero.display.fx.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.hero.display.fx.CharacterPane;
import net.sf.anathema.hero.display.fx.MultipleContentView;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class TaskedCharacterPane implements CharacterPane {

  private final MigPane paneContainer = new MigPane(new LC().wrapAfter(1));
  private final MigPane viewPanel = new MigPane();
  private final MigPane content = new MigPane(LayoutUtils.fillWithoutInsets());

  public TaskedCharacterPane() {
    content.add(paneContainer, new CC().alignY("top"));
    content.add(viewPanel, new CC().push().grow());
  }

  @Override
  public MultipleContentView addMultipleContentView(String header) {
    return new TaskedMultipleContentView(header, paneContainer, viewPanel);
  }

  public Node getNode() {
    return content;
  }
}