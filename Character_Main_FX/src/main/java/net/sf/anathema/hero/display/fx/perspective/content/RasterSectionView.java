package net.sf.anathema.hero.display.fx.perspective.content;

import javafx.scene.Node;
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
    migPane.add(viewToAdd.getNode());
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
}
