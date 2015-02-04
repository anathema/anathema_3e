package net.sf.anathema.hero.display.fx.perspective.navigation;

import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.perspective.CharacterButtonDto;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.UpdatingHeroesGridView;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.display.fx.creation.FxHeroSplatCreator;
import net.sf.anathema.library.fx.tool.FxBaseTool;
import net.sf.anathema.library.interaction.model.Tool;

import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.library.fx.layout.LayoutUtils.clipToSize;

public class HeroPoolFxView implements UpdatingHeroesGridView {
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final HBox gridPane = new HBox();
  private final Map<HeroIdentifier, HeroPoolButton> buttonsByIdentifier = new HashMap<>();

  public HeroPoolFxView() {
    gridPane.setMinWidth(0);
    clipToSize(gridPane);
  }

  @Override
  public void addButton(CharacterButtonDto dto, Selector<HeroIdentifier> characterSelector) {
    createGridButton(dto, characterSelector);
  }

  @Override
  public void selectButton(HeroIdentifier identifier) {
    buttonsByIdentifier.get(identifier).setSelected(true);
  }

  @Override
  public void updateButton(CharacterButtonDto dto) {
    buttonsByIdentifier.get(dto.identifier).setContent(dto);
  }

  @Override
  public CharacterTemplateCreator createNewCharacter(Tool caller) {
    return new FxHeroSplatCreator(((FxBaseTool) caller).getNode());
  }

  private HeroPoolButton createGridButton(CharacterButtonDto dto,
                                          Selector<HeroIdentifier> characterSelector) {
    HeroPoolButton heroGridButton = new HeroPoolButton();
    heroGridButton.initContent(dto, characterSelector);
    heroGridButton.setToggleGroup(toggleGroup);
    buttonsByIdentifier.put(dto.identifier, heroGridButton);
    gridPane.getChildren().add(heroGridButton.getNode());
    return heroGridButton;
  }

  public Node getNode() {
    return gridPane;
  }

  public void clear() {
    gridPane.getChildren().clear();
  }
}