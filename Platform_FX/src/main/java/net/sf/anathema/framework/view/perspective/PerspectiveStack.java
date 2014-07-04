package net.sf.anathema.framework.view.perspective;

import javafx.scene.Node;
import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.framework.view.util.FxStack;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import org.tbee.javafx.scene.layout.MigPane;

public class PerspectiveStack {
  private final MigPane cardPanel = new MigPane(LayoutUtils.fillWithoutInsets());
  private final FxStack perspectiveStack = new FxStack(cardPanel);
  private final ApplicationModel model;
  private final Environment environment;
  private final UiEnvironment uiEnvironment;

  public PerspectiveStack(ApplicationModel model, Environment environment, UiEnvironment uiEnvironment) {
    this.model = model;
    this.environment = environment;
    this.uiEnvironment = uiEnvironment;
  }

  public void add(Perspective perspective) {
    Container container = new CardContainer(getIdFor(perspective), perspectiveStack);
    perspective.initContent(container, model, environment, uiEnvironment);
  }

  public void show(Perspective perspective) {
    perspectiveStack.show(getIdFor(perspective));
  }

  private Identifier getIdFor(Perspective perspective) {
    return new SimpleIdentifier(perspective.getClass().getCanonicalName());
  }

  public Node getContent() {
    return cardPanel;
  }

  public void showFirst() {
    perspectiveStack.showFirst();
  }
}
