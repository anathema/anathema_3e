package net.sf.anathema.platform.fx.utility;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import net.sf.anathema.library.fx.view.ViewFactory;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.utility.UtilityAutoCollector;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;

public class UtilityPaneFactory implements ViewFactory {

  private final UtilityStack utilityStack;
  private final UtilitySelectionBar selectionBar;
  private final Resources resources;
  private final ObjectFactory objectFactory;

  public UtilityPaneFactory(ApplicationModel model, Environment environment, ObjectFactory objectFactory,
                            UiEnvironment uiEnvironment) {
    this.resources = environment;
    this.objectFactory = objectFactory;
    this.utilityStack = new UtilityStack(model, environment, uiEnvironment);
    this.selectionBar = new UtilitySelectionBar(utilityStack);
  }

  @Override
  public Node createContent() {
    Collection<UtilityPerspective> sortedPerspectives = objectFactory.instantiateOrdered(UtilityAutoCollector.class);
    for (final UtilityPerspective perspective : sortedPerspectives) {
      utilityStack.add(perspective);
      selectionBar.addPerspective(perspective, resources);
    }
    final MigPane contentPanel = new MigPane(LayoutUtils.fillWithoutInsets());
    contentPanel.add(selectionBar.getContent(), new CC().dockNorth());
    contentPanel.add(utilityStack.getContent(), new CC().push().grow());
    selectionBar.selectFirstButton();
    return contentPanel;
  }
}