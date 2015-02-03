package net.sf.anathema.hero.display.fx.perspective.content;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.application.SubViewRegistry;
import net.sf.anathema.hero.individual.view.SectionView;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import net.sf.anathema.library.interaction.model.Command;
import org.tbee.javafx.scene.layout.MigPane;

public class RasterSectionView implements SectionView, NodeHolder {

  private SubViewRegistry subViewFactory;
  private Command initializationCommand;
  private MigPane migPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(3));
  private boolean isEmpty = true;

  public RasterSectionView(SubViewRegistry subViewFactory, Command initializationCommand) {
    this.subViewFactory = subViewFactory;
    this.initializationCommand = initializationCommand;
  }

  @Override
  public <T> T addView(String title, Class<T> viewClass) {
    this.isEmpty = false;
    T newView = subViewFactory.get(viewClass);
    NodeHolder viewToAdd = (NodeHolder) newView;
    migPane.add(createContainer(viewToAdd, title), new CC().alignY("t"));
    return newView;
  }

  @Override
  public void finishInitialization() {
    if (isEmpty) {
      return;
    }
    initializationCommand.execute();
  }

  @Override
  public Node getNode() {
    return migPane;
  }

  private Node createContainer(NodeHolder content, String name) {
    MigPane viewComponent = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));
    MigPane titlePane = new MigPane(LayoutUtils.fillWithoutInsets());
    Label title = new Label(name);
    title.setStyle("-fx-font-weight: bold");
    titlePane.add(title);
    titlePane.add(new Separator(), new CC().pushX().growX());
    viewComponent.add(titlePane, new CC().pushX().growX());
    viewComponent.add(content.getNode(), new CC().push().grow());
    return viewComponent;
  }
}
