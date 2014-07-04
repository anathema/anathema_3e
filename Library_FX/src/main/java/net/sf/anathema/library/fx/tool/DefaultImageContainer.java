package net.sf.anathema.library.fx.tool;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.anathema.library.number.Area;

public class DefaultImageContainer implements ImageContainer {
  private Image image;

  public DefaultImageContainer(Image image) {
    this.image = image;
  }

  @Override
  public Area getArea() {
    return new Area(image.getWidth(), image.getHeight());
  }

  @Override
  public void displayIn(ImageView imageView) {
    imageView.setImage(image);
  }
}