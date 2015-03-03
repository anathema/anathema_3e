package net.sf.anathema.hero.display.fx.perspective;

import javafx.scene.Node;

import net.miginfocom.layout.CC;
import net.sf.anathema.hero.application.perspective.HeroRoster;
import net.sf.anathema.hero.application.perspective.UpdatingHeroesGridView;
import net.sf.anathema.hero.display.fx.perspective.content.StackView;
import net.sf.anathema.hero.display.fx.perspective.navigation.FxHeroPoolNavigation;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.interaction.view.InteractionView;

import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;

public class HeroesStanceView {

  private final MigPane contentPane = new MigPane(fillWithoutInsets().wrapAfter(1).gridGap("0", "0"));
  private final StackView stackView = new StackView();
  private final FxHeroPoolNavigation navigation;

  public HeroesStanceView() {
    this.navigation = new FxHeroPoolNavigation();
    contentPane.add(navigation.getNode(), new CC().growX());
    contentPane.add(stackView.getComponent(), new CC().grow().push());
  }

  public InteractionView getCenterInteractionView() {
    return navigation.getCenterInteraction();
  }

  public InteractionView getFrontInteractionView() {
    return navigation.getWestInteraction();
  }

  public UpdatingHeroesGridView getGridView() {
    return navigation;
  }

  public StackView getStackView() {
    return stackView;
  }

  public Node getNode() {
    return contentPane;
  }

  public Tool createLeaveTool() {
    return navigation.createBigToolAtEastSide();
  }

  public HeroRoster createHeroRoster() {
    FxHeroRoster roster = new FxHeroRoster();
    stackView.addView(roster.getIdentifier(), roster);
    return roster;
  }
}