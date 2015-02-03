package net.sf.anathema.hero.display.fx.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.application.perspective.HeroesGridView;
import net.sf.anathema.hero.display.fx.perspective.content.StackView;
import net.sf.anathema.hero.display.fx.perspective.navigation.FxHeroPoolNavigation;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.interaction.view.InteractionView;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;

public class HeroesStanceView {

  private final MigPane contentPane = new MigPane(fillWithoutInsets().wrapAfter(1).gridGap("0", "0"));
  private final StackView stackView = new StackView();
  private final FxHeroPoolNavigation navigation;

  public HeroesStanceView(UiEnvironment uiEnvironment) {
    this.navigation = new FxHeroPoolNavigation(uiEnvironment);
    contentPane.add(navigation.getNode(), new CC().growX());
    contentPane.add(stackView.getComponent(), new CC().grow().push());
  }

  public InteractionView getBackInteractionView() {
    return navigation;
  }

  public InteractionView getFrontInteractionView() {
    return navigation.getFrontInteraction();
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
    return navigation.createBigToolAtTheEnd();
  }
}