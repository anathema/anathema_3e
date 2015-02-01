package net.sf.anathema.hero.display.fx.perspective;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.application.perspective.CharacterButtonDto;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import net.sf.anathema.library.resources.ResourceLoader;
import org.tbee.javafx.scene.layout.MigPane;

import java.io.InputStream;

public class CharacterGridButton {

  public static final int IMAGE_SIZE = 40;
  private final ImageView imageView = new ImageView();
  private final ToggleButton button = new ToggleButton();
  private final Label text = new Label();

  public CharacterGridButton() {
    HBox imageBorder = new HBox();
    imageBorder.getChildren().add(imageView);
    imageBorder.setMargin(imageView, new Insets(2));
    imageBorder.getStyleClass().add("image");
    text.setWrapText(true);
    text.setAlignment(Pos.TOP_CENTER);
    MigPane buttonGraphic = new MigPane(LayoutUtils.withoutInsets().wrapAfter(1).gridGap("0","2"));
    buttonGraphic.add(imageBorder, new CC().alignX("center"));
    buttonGraphic.add(text, new CC().alignX("center"));
    button.getStyleClass().add("character-grid-button");
    button.setGraphic(buttonGraphic);
  }

  public void initContent(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector) {
    button.setOnAction(new CharacterSelected(characterSelector, dto.identifier));
    setContent(dto);
  }

  public void setContent(CharacterButtonDto dto) {
    text.setText(dto.text);
    imageView.setImage(createImage(dto));
    if (dto.isDirty) {
      text.getStyleClass().add("dirty");
      text.getStyleClass().remove("clean");
    } else {
      text.getStyleClass().add("clean");
      text.getStyleClass().remove("dirty");
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
