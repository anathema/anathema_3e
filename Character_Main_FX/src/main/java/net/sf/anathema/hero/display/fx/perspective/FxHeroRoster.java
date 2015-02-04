package net.sf.anathema.hero.display.fx.perspective;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import net.sf.anathema.hero.application.perspective.CharacterButtonDto;
import net.sf.anathema.hero.application.perspective.HeroRoster;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.display.fx.perspective.navigation.HeroPoolButton;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.Stylesheet;

public class FxHeroRoster implements HeroRoster, NodeHolder {

  private final TilePane tiles = new TilePane();

  public FxHeroRoster() {
    tiles.setPrefRows(1);
    new Stylesheet("skin/character/charactergridbutton.css").applyToParent(tiles);
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
    return tiles;
  }
}