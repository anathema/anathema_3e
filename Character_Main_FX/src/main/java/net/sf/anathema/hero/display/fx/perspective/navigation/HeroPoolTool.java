package net.sf.anathema.hero.display.fx.perspective.navigation;

import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBase;
import javafx.scene.image.ImageView;

public class HeroPoolTool extends HeroPoolBaseTool {

  public static HeroPoolTool createTool(ButtonBase button) {
    HeroPoolTool heroPoolTool = new HeroPoolTool(button);
    heroPoolTool.markAsClean();
    return heroPoolTool;
  }

  public HeroPoolTool(ButtonBase button) {
    super(button, new ImageView());
  }

  public void markAsDirty() {
    ObservableList<String> styles = getNode().getStyleClass();
    styles.add("dirty");
    styles.remove("clean");
  }

  public void markAsClean() {
    ObservableList<String> styles = getNode().getStyleClass();
    styles.add("clean");
    styles.remove("dirty");
  }
}
