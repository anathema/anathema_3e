package net.sf.anathema.platform.fx.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import net.sf.anathema.library.fx.view.ViewFactory;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.stance.StanceAutoCollector;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StancePaneFactory implements ViewFactory {

  private final PerspectiveStack perspectiveStack;
  private final Resources resources;
  private final ObjectFactory objectFactory;

  public StancePaneFactory(ApplicationModel model, Environment environment, ObjectFactory objectFactory,
                           UiEnvironment uiEnvironment) {
    this.resources = environment;
    this.objectFactory = objectFactory;
    this.perspectiveStack = new PerspectiveStack(model, environment, uiEnvironment);
  }

  @Override
  public Node createContent() {
    Collection<Stance> sortedStances = objectFactory.instantiateOrdered(StanceAutoCollector.class);
    for (final Stance stance : sortedStances) {
      perspectiveStack.add(stance);
    }
    linkStances(new ArrayList<>(sortedStances));
    final MigPane contentPanel = new MigPane(LayoutUtils.fillWithoutInsets());
    contentPanel.add(perspectiveStack.getContent(), new CC().push().grow());
    perspectiveStack.showFirst();
    return contentPanel;
  }

  private void linkStances(List<Stance> sortedStances) {
    if (sortedStances.size()< 2) {
      return;
    }
    List<Stance> loopList = new ArrayList<>(sortedStances);
    loopList.add(sortedStances.get(0));
    for (int index = 0; index < loopList.size() - 1; index++) {
      Stance stance = loopList.get(index);
      Stance followUpStance = loopList.get(index + 1);
      Tool leaveStanceTool = stance.createLeaveTool();
      leaveStanceTool.setCommand(() -> perspectiveStack.show(followUpStance));
      followUpStance.configureEnterTool(leaveStanceTool);
    }
  }
}