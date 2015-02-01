package net.sf.anathema.hero.display.fx.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.application.perspective.HeroesGridView;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.interaction.view.InteractionView;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;

public class HeroesStanceView {

  private final MigPane contentPane = new MigPane(fillWithoutInsets().wrapAfter(1));
  private final StackView stackView = new StackView();
  private final FxHeroesNavigation navigation;

  public HeroesStanceView(UiEnvironment uiEnvironment) {
    this.navigation = new FxHeroesNavigation(uiEnvironment);
    contentPane.add(navigation.getNode(), new CC().growX());
    contentPane.add(stackView.getComponent(), new CC().grow().push());
  }

  public InteractionView getInteractionView() {
    return navigation;
  }

  public HeroesGridView getGridView() {
    return navigation;
  }

  public StackView getStackView() {
    return stackView;
  }

  public Node getNode() {
    return contentPane;
  }

  public Tool createLeaveTool() {
    return navigation.createLeaveTool();
  }
}