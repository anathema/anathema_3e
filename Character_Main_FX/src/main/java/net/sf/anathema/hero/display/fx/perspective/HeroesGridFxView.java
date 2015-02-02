package net.sf.anathema.hero.display.fx.perspective;

import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.perspective.CharacterButtonDto;
import net.sf.anathema.hero.application.perspective.HeroesGridView;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.display.fx.creation.FxHeroSplatCreator;
import net.sf.anathema.platform.fx.environment.DialogFactory;

import java.util.HashMap;
import java.util.Map;

public class HeroesGridFxView implements HeroesGridView {
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final HBox gridPane = new HBox();
  private final Map<HeroIdentifier, HeroGridButton> buttonsByIdentifier = new HashMap<>();
  private final DialogFactory dialogFactory;

  public HeroesGridFxView(DialogFactory dialogFactory) {
    this.dialogFactory = dialogFactory;
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
  public CharacterTemplateCreator createNewCharacter() {
    return new FxHeroSplatCreator(dialogFactory);
  }

  private HeroGridButton createGridButton(CharacterButtonDto dto,
                                               Selector<HeroIdentifier> characterSelector) {
    HeroGridButton heroGridButton = new HeroGridButton();
    heroGridButton.initContent(dto, characterSelector);
    heroGridButton.setToggleGroup(toggleGroup);
    buttonsByIdentifier.put(dto.identifier, heroGridButton);
    gridPane.getChildren().add(heroGridButton.getNode());
    return heroGridButton;
  }

  public Node getNode() {
    return gridPane;
  }
}