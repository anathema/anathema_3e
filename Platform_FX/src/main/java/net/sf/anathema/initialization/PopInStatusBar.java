package net.sf.anathema.initialization;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import net.miginfocom.layout.LC;
import net.sf.anathema.framework.view.messaging.MessageTypeImagePaths;
import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.platform.tool.ImageContainer;
import net.sf.anathema.platform.tool.LoadImage;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.action.AbstractAction;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;

import static javafx.util.Duration.millis;
import static net.sf.anathema.lib.message.MessageDuration.Temporary;
import static org.controlsfx.control.PopOver.ArrowLocation.BOTTOM_LEFT;

public class PopInStatusBar implements StatusBar {
  private NotificationPane pane;
  private final ImageView imageView = new ImageView();

  public PopInStatusBar(NotificationPane pane) {
    this.pane = pane;
    pane.setShowFromTop(false);
    pane.setGraphic(imageView);
  }

  @Override
  public void setLatestMessage(Message message) {
    pane.setText(message.getText());
    loadImageForMessage(message, imageView);
    pane.show();
    if (message.getDuration() == Temporary) {
      new Timeline(new KeyFrame(millis(2500), event -> pane.hide())).play();
    }
  }

  @Override
  public void whenAllMessagesAreRequested(Command command) {
    pane.getActions().add(new AbstractAction("Show all") {
      @Override
      public void handle(ActionEvent actionEvent) {
        command.execute();
      }
    });
  }

  @Override
  public void showMessages(Collection<Message> messages) {
    MigPane content = new MigPane(new LC().wrapAfter(1));
    for (Message message : messages) {
      Label label = new Label();
      label.setText(message.getText());
      ImageView imageView2 = new ImageView();
      label.setGraphic(imageView2);
      loadImageForMessage(message, imageView2);
      content.add(label);
    }
    PopOver popOver = new PopOver(content);
    popOver.setArrowLocation(BOTTOM_LEFT);
    popOver.setAutoHide(true);
    popOver.setAutoFix(true);
    popOver.show(imageView);
  }

  private void loadImageForMessage(Message message, ImageView view) {
    LoadImage image = new LoadImage(new MessageTypeImagePaths().getIconPath(message.getType()));
    ImageContainer container = image.run();
    container.displayIn(view);
  }
}