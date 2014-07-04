package net.sf.anathema.character.equipment.creation.view.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import net.sf.anathema.library.event.BooleanChangedListener;
import net.sf.anathema.library.view.BooleanValueView;
import org.jmock.example.announcer.Announcer;

public class FxBooleanView implements BooleanValueView {

  private final CheckBox checkBox = new CheckBox();
  private final Announcer<BooleanChangedListener> announcer = Announcer.to(BooleanChangedListener.class);

  public FxBooleanView() {
    checkBox.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        announcer.announce().valueChanged(checkBox.isSelected());
      }
    });
  }

  @Override
  public void setSelected(boolean selected) {
    checkBox.setSelected(selected);
  }

  @Override
  public void addChangeListener(BooleanChangedListener listener) {
    announcer.addListener(listener);
  }

  public Node getNode() {
    return checkBox;
  }
}
