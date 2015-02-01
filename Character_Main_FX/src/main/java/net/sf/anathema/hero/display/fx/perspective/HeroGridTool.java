package net.sf.anathema.hero.display.fx.perspective;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import net.miginfocom.layout.CC;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import net.sf.anathema.library.fx.tool.FxBaseTool;
import net.sf.anathema.library.fx.tool.ImageContainer;
import net.sf.anathema.library.fx.tool.LoadImage;
import net.sf.anathema.library.resources.RelativePath;
import org.tbee.javafx.scene.layout.MigPane;

public class HeroGridTool extends FxBaseTool {
  public static final int IMAGE_SIZE = 40;

  public static HeroGridTool createTool(ButtonBase button) {
    HeroGridTool heroGridTool = new HeroGridTool(button);
    heroGridTool.markAsClean();
    return heroGridTool;
  }

  private final ImageView imageView = new ImageView();
  private final Label text = new Label();

  public HeroGridTool(ButtonBase button) {
    super(button, new ImageView());
    HBox imageBorder = new HBox();
    imageBorder.getChildren().add(imageView);
    imageBorder.setMargin(imageView, new Insets(2));
    imageBorder.getStyleClass().add("image");
    text.getStyleClass().add("hero-name");
    MigPane buttonGraphic = new MigPane(LayoutUtils.withoutInsets().wrapAfter(1).gridGap("0","2"));
    buttonGraphic.add(imageBorder, new CC().alignX("center"));
    buttonGraphic.add(text, new CC().alignX("center"));
    button.getStyleClass().add("character-grid-button");
    button.setGraphic(buttonGraphic);
  }

  @Override
  public void setText(String text) {
    this.text.setText(text);
  }

  @Override
  public void setIcon(RelativePath relativePath) {
    ImageContainer image = new LoadImage(relativePath, IMAGE_SIZE).run();
    image.displayIn(imageView);
  }

  public void markAsDirty() {
    text.getStyleClass().add("dirty");
    text.getStyleClass().remove("clean");
  }

  public void markAsClean() {
    text.getStyleClass().add("clean");
    text.getStyleClass().remove("dirty");
  }
}
