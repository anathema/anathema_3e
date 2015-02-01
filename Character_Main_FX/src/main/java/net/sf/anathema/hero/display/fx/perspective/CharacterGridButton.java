package net.sf.anathema.hero.display.fx.perspective;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import net.sf.anathema.hero.application.perspective.CharacterButtonDto;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;
import net.sf.anathema.library.resources.ResourceLoader;

import java.io.InputStream;

public class CharacterGridButton {

  public static final int IMAGE_SIZE = 40;
  private final ImageView imageView = new ImageView();
  private final HBox imageBox = new HBox();
  private final ToggleButton button = new ToggleButton("", imageBox);

  public CharacterGridButton() {
    button.setMinHeight(90);
    button.setMaxHeight(90);
    button.setMaxWidth(70);
    button.setMinWidth(70);
    button.setContentDisplay(ContentDisplay.TOP);
    button.setAlignment(Pos.TOP_CENTER);
    imageBox.setAlignment(Pos.TOP_CENTER);
    imageBox.getChildren().add(imageView);
    imageBox.getStyleClass().add("image");
    button.getStyleClass().add("character-grid-button");
    button.setWrapText(true);
    button.setTextOverrun(OverrunStyle.WORD_ELLIPSIS);
  }

  public void initContent(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector) {
    button.setOnAction(new CharacterSelected(characterSelector, dto.identifier));
    setContent(dto);
  }

  public void setContent(CharacterButtonDto dto) {
    button.setText(dto.text);
    imageView.setImage(createImage(dto));
    if (dto.isDirty) {
      button.getStyleClass().add("dirty");
      button.getStyleClass().remove("clean");
    } else {
      button.getStyleClass().add("clean");
      button.getStyleClass().remove("dirty");
    }
  }

  private Image createImage(CharacterButtonDto dto) {
    ResourceLoader resourceLoader = new ResourceLoader();
    InputStream imageStream = resourceLoader.loadResource(dto.pathToImage);
    return new Image(imageStream, IMAGE_SIZE, IMAGE_SIZE, true, true);
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
