package net.sf.anathema.hero.display.fx.perspective;

import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.application.perspective.CharacterButtonDto;
import net.sf.anathema.hero.application.perspective.HeroRoster;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.display.fx.perspective.navigation.HeroPoolButton;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.Stylesheet;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxHeroRoster implements HeroRoster, NodeHolder {
  
  private MigPane content = new MigPane(withoutInsets().wrapAfter(1), new AC().index(0).shrink().shrinkPrio(200));
  private final MigPane testBar = new MigPane(withoutInsets().gridGap("10", "0"));
  private final TilePane tiles = new TilePane();

  public FxHeroRoster() {
    content.add(testBar, new CC().growX());
    content.add(tiles, new CC().grow().push());
    tiles.setPrefRows(1);
    new Stylesheet("skin/character/charactergridbutton.css").applyToParent(tiles);
    new Stylesheet("skin/character/heroroster.css").applyToParent(tiles);
    new Stylesheet("skin/character/hero-view.css").applyToParent(content);
    testBar.getStyleClass().add("hero-link-bar");
  }

  @Override
  public void clear() {
    tiles.getChildren().clear();
  }

  @Override
  public HeroIdentifier getIdentifier() {
    return new HeroIdentifier("HeroRoster");
  }

  @Override
  public void addButton(CharacterButtonDto dto, Selector<HeroIdentifier> characterSelector) {
    HeroPoolButton heroGridButton = new HeroPoolButton();
    heroGridButton.initContent(dto, characterSelector);
    Node node = heroGridButton.getNode();
    tiles.getChildren().add(node);
  }

  @Override
  public Node getNode() {
    return content;
  }
}