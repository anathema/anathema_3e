package net.sf.anathema.framework.view.status;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import net.miginfocom.layout.LC;
import net.sf.anathema.framework.view.messaging.MessageTypeImagePaths;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.message.Message;
import net.sf.anathema.platform.messaging.StatusBar;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.action.AbstractAction;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;

import static javafx.util.Duration.millis;
import static net.sf.anathema.library.message.MessageDuration.Temporary;
import static org.controlsfx.control.PopOver.ArrowLocation.BOTTOM_LEFT;

public class PopInStatusBar implements StatusBar {
  private final ImageView imageView = new ImageView();
  private final NotificationPane pane;
  private final NotificationWithGraphicAndText paneWithGraphicAndText;

  public PopInStatusBar(NotificationPane pane) {
    this.pane = pane;
    this.paneWithGraphicAndText = new NotificationWithGraphicAndText(pane);
    pane.setShowFromTop(false);
    paneWithGraphicAndText.setGraphic(imageView);
  }

  @Override
  public void setLatestMessage(Message message) {
    setGraphicAndText(message, paneWithGraphicAndText);
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
      LabelWithGraphicAndText labelWithGraphicAndText = new LabelWithGraphicAndText(label);
      setGraphicAndText(message, labelWithGraphicAndText);
      content.add(label);
    }
    showInPopOver(content);
  }

  private void showInPopOver(MigPane content) {
    PopOver popOver = new PopOver(content);
    popOver.setArrowLocation(BOTTOM_LEFT);
    popOver.setAutoHide(true);
    popOver.setAutoFix(true);
    popOver.show(imageView);
  }

  private void setGraphicAndText(Message message, WithGraphicAndText graphicAndText) {
    graphicAndText.setText(message.getText());
    graphicAndText.setImage(new MessageTypeImagePaths().getIconPath(message.getType()));
  }
}