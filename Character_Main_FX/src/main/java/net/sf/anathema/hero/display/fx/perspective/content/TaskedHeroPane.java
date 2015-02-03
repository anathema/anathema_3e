package net.sf.anathema.hero.display.fx.perspective.content;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.hero.display.fx.HeroPane;
import net.sf.anathema.hero.display.fx.MultipleContentView;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class TaskedHeroPane implements HeroPane {

  private final MigPane paneContainer = new MigPane(new LC().wrapAfter(1));
  private final MigPane viewPanel = new MigPane();
  private final MigPane content = new MigPane(LayoutUtils.fillWithoutInsets());

  public TaskedHeroPane() {
    content.add(paneContainer, new CC().alignY("top"));
    content.add(viewPanel, new CC().push().grow());
  }

  @Override
  public MultipleContentView addMultipleContentView(String header) {
    TaskedPaneMultipleContentInitializer initializer = new TaskedPaneMultipleContentInitializer(header, paneContainer);
    return new HeroMultipleContentView(viewPanel, initializer);
  }

  public Node getNode() {
    return content;
  }
}