package net.sf.anathema.library.fx.tool;

import javafx.scene.image.Image;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.ResourceLoader;

import java.io.InputStream;

import static net.sf.anathema.library.presenter.AgnosticUIConfiguration.NO_ICON;

public class LoadImage {
  private final RelativePath relativePath;
  private final double targetSize;

  public LoadImage(RelativePath relativePath) {
    this(relativePath, 0d);
  }

  public LoadImage(RelativePath relativePath, double targetSize) {
    this.relativePath = relativePath;
    this.targetSize = targetSize;
  }

  public ImageContainer run() {
    if (relativePath == NO_ICON) {
      return new NullImageContainer();
    }
    ResourceLoader resourceLoader = new ResourceLoader();
    InputStream imageStream = resourceLoader.loadResource(relativePath);
    return new DefaultImageContainer(new Image(imageStream, targetSize, targetSize, true, true));
  }
}