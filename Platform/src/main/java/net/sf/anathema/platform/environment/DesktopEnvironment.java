package net.sf.anathema.platform.environment;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Path;

import static java.awt.Desktop.isDesktopSupported;

public class DesktopEnvironment {

  public static boolean isAutoOpenSupported() {
    return isDesktopSupported();
  }

  public static boolean isOpenSupported() {
    return isDesktopSupported();
  }

  public static void openOnDesktop(Path path) throws IOException {
    Desktop.getDesktop().open(path.toFile());
  }
}