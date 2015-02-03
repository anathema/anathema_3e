package net.sf.anathema.hero.display.fx.perspective.navigation;

import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import net.sf.anathema.hero.application.perspective.CharacterButtonDto;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;

public class HeroPoolButton {

  private final HeroPoolTool tool;
  private final ToggleButton button = new ToggleButton();

  public HeroPoolButton() {
    this.tool = HeroPoolTool.createTool(button);
   }

  public void initContent(CharacterButtonDto dto, Selector<HeroIdentifier> characterSelector) {
    button.setOnAction(new HeroSelected(characterSelector, dto.identifier));
    setContent(dto);
  }

  public void setContent(CharacterButtonDto dto) {
    tool.setText(dto.text);
    tool.setIcon(dto.pathToImage);
    if (dto.isDirty) {
      tool.markAsDirty();
    } else {
      tool.markAsClean();
     }
  }

  public Node getNode() {
    return button;
  }

  public void setToggleGroup(ToggleGroup toggleGroup) {
    button.setToggleGroup(toggleGroup);
  }

  public void setSelected(boolean selected) {
    button.setSelected(selected);
  }
}
