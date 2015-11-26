package net.sf.anathema.platform.fx.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;
import net.sf.anathema.library.exception.ExceptionHandler;
import net.sf.anathema.library.resources.Resources;

import java.io.PrintWriter;
import java.io.StringWriter;

public class FxDialogExceptionHandler implements ExceptionHandler {

  private final Resources resources;
  private final Window stage;

  public FxDialogExceptionHandler(Resources resources, Window stage) {
    this.resources = resources;
    this.stage = stage;
  }

  @Override
  public void handle(Throwable throwable) {
    if (throwable instanceof Exception) {
      String message = getString("CentralExceptionHandling.ExceptionOccured.Message");
      indicateException((Exception) throwable, message);
    } else {
      String message = getString("CentralExceptionHandling.ErrorOccured.Message");
      indicateError(throwable, message);
    }
  }

  @Override
  public void handle(Throwable throwable, String message) {
    if (throwable instanceof Exception) {
      indicateException((Exception) throwable, message);
    } else {
      indicateError(throwable, message);
    }
  }

  @SuppressWarnings("UnusedParameters")
  protected void indicateError(final Throwable throwable, final String message) {
    String title = getString("CentralExceptionHandling.ErrorOccured.Title");
    showDialog(title, message, throwable);
    System.exit(0);
  }

  private void showDialog(String title, String message, Throwable throwable) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(title);
    alert.setContentText(message);
    StringWriter stringWriter = new StringWriter();
    PrintWriter printwriter = new PrintWriter(stringWriter);
    throwable.printStackTrace(printwriter);
    String exceptionText = stringWriter.toString();

    Label label = new Label("The exception stacktrace was:");

    TextArea textArea = new TextArea(exceptionText);
    textArea.setEditable(false);
    textArea.setWrapText(true);

    textArea.setMaxWidth(Double.MAX_VALUE);
    textArea.setMaxHeight(Double.MAX_VALUE);
    GridPane.setVgrow(textArea, Priority.ALWAYS);
    GridPane.setHgrow(textArea, Priority.ALWAYS);

    GridPane expContent = new GridPane();
    expContent.setMaxWidth(Double.MAX_VALUE);
    expContent.add(label, 0, 0);
    expContent.add(textArea, 0, 1);

    alert.getDialogPane().setExpandableContent(expContent);
    alert.showAndWait();
  }

  protected void indicateException(final Exception exception, final String message) {
    String title = getString("CentralExceptionHandling.ExceptionOccured.Title");
    showDialog(title, message, exception);
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }
}