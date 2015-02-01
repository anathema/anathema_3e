package net.sf.anathema.platform.fx.perspective;

import javafx.scene.Node;
import net.sf.anathema.library.fx.tool.FxButtonTool;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.messaging.MessageCategory;
import net.sf.anathema.platform.stance.StanceAutoCollector;

@StanceAutoCollector
@Weight(weight = 2)
public class UtilityStance implements Stance {
  @Override
  public void initContent(Container container, ApplicationModel applicationModel, Environment environment,
                          UiEnvironment uiEnvironment) {
    UtilityPaneFactory factory = new UtilityPaneFactory(applicationModel, environment,
            environment.getObjectFactory(), uiEnvironment);
    Node content = factory.createContent();
    container.setContent(content);
  }

  @Override
  public MessageCategory getMessageCategory() {
    return new MessageCategory("Utility");
  }

  @Override
  public Tool createLeaveTool() {
    FxButtonTool tool = FxButtonTool.ForToolbar();
    // todo: Move toolbar from factory to this class and add
    return tool;
  }

  @Override
  public void configureEnterTool(Tool tool) {
    tool.setText("Editors & Settings");
  }
}
