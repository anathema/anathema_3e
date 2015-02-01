package net.sf.anathema.platform.fx.perspective;

import net.miginfocom.layout.CC;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.messaging.MessageCategory;
import net.sf.anathema.platform.stance.StanceAutoCollector;
import net.sf.anathema.platform.utility.UtilityAutoCollector;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;

@StanceAutoCollector
@Weight(weight = 2)
public class UtilityStance implements Stance {

  private UtilitySelectionBar selectionBar;

  @Override
  public void initContent(Container container, ApplicationModel applicationModel, Environment environment,
                          UiEnvironment uiEnvironment) {
    PerspectiveStack perspectiveStack = new PerspectiveStack(applicationModel, environment, uiEnvironment);
    selectionBar = new UtilitySelectionBar(perspectiveStack);
    Collection<UtilityPerspective> sortedPerspectives = collectSortedPerspectives(environment);
    for (UtilityPerspective perspective : sortedPerspectives) {
      perspectiveStack.add(perspective);
      selectionBar.addPerspective(perspective, environment);
    }
    MigPane contentPanel = new MigPane(LayoutUtils.fillWithoutInsets());
    contentPanel.add(selectionBar.getContent(), new CC().dockNorth());
    contentPanel.add(perspectiveStack.getContent(), new CC().push().grow());
    selectionBar.selectFirstButton();
    container.setContent(contentPanel);
  }

  private Collection<UtilityPerspective> collectSortedPerspectives(Environment environment) {
    ObjectFactory objectFactory = environment.getObjectFactory();
    return objectFactory.instantiateOrdered(UtilityAutoCollector.class);
  }

  @Override
  public MessageCategory getMessageCategory() {
    return new MessageCategory("Utility");
  }

  @Override
  public Tool createLeaveTool() {
    return selectionBar.addTool();
  }

  @Override
  public void configureEnterTool(Tool tool) {
    tool.setText("Editors & Settings");
  }
}
