package net.sf.anathema.hero.display.fx.creation;

import javafx.scene.Node;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.hero.application.creation.ButtonPanel;
import net.sf.anathema.hero.application.creation.CharacterCreationView;

import org.controlsfx.control.PopOver;
import org.tbee.javafx.scene.layout.MigPane;

public class FxHeroCreationView implements CharacterCreationView {

  private final MigPane component = new MigPane(new LC().insets("5").gridGapY("10"));
  private final PopOver dialog;
  private Node parent;

  public FxHeroCreationView(Node parent) {
    this.parent = parent;
    this.dialog = new PopOver(parent);
  }

  @Override
  public ButtonPanel addToggleButtonPanel() {
    FxButtonPanel panel = new FxButtonPanel();
    component.add(panel.getNode(), new CC().grow().pushY());
    return panel;
  }

  @Override
  public void show() {
    dialog.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
    dialog.setAutoHide(true);
    dialog.setAutoFix(true);
    dialog.setContentNode(component);
    dialog.show(parent);
  }

  @Override
  public void close() {
    dialog.hide();
  }
}