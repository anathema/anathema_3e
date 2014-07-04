package net.sf.anathema.platform.tool;

import javafx.scene.image.ImageView;
import net.sf.anathema.library.number.Area;

public interface ImageContainer {
  Area getArea();

  void displayIn(ImageView imageView);
}
