package net.sf.anathema.framework.view.status;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import net.sf.anathema.lib.file.RelativePath;

public class LabelWithGraphicAndText implements WithGraphicAndText{
  private final ImageView imageView = new ImageView();
  private final Label label;

  public LabelWithGraphicAndText(Label label) {
    this.label = label;
    label.setGraphic(imageView);
  }

  @Override
  public void setText(String text) {
    label.setText(text);
  }

  @Override
  public void setImage(RelativePath iconPath) {
    NotificationWithGraphicAndText.setImage(iconPath, imageView);
  }
}
