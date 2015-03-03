package net.sf.anathema.hero.display.fx.perspective.navigation;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import net.miginfocom.layout.CC;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import net.sf.anathema.library.fx.tool.FxBaseTool;
import net.sf.anathema.library.fx.tool.ImageClosure;
import net.sf.anathema.library.fx.tool.ImageContainer;
import net.sf.anathema.library.fx.tool.LoadImage;
import net.sf.anathema.library.resources.RelativePath;

import org.tbee.javafx.scene.layout.MigPane;

public class HeroPoolBaseTool extends FxBaseTool {
  public static final int IMAGE_SIZE = 40;
  protected final ImageView imageView = new ImageView();
  protected final Label text = new Label();

  public HeroPoolBaseTool(ButtonBase button, ImageView overlay, ImageClosure... actionsOnLoad) {
    super(button, overlay, actionsOnLoad);
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
}
