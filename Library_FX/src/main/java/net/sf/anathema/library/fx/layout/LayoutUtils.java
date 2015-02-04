package net.sf.anathema.library.fx.layout;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import net.miginfocom.layout.LC;

public class LayoutUtils {

  public static LC withoutInsets() {
    return new LC().insets("0");
  }

  public static LC fillWithoutInsets() {
    return withoutInsets().fill();
  }
  
  public static void clipToSize(Region region){
    Rectangle clip = new Rectangle(0, 0);
    region.setClip(clip);
    clip.widthProperty().bind(region.widthProperty());
    clip.heightProperty().bind(region.heightProperty());
  }
}