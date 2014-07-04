package net.sf.anathema;

import javafx.application.Application;
import javafx.stage.Stage;
import net.sf.anathema.framework.environment.fx.FxUiEnvironment;
import net.sf.anathema.framework.repository.tree.FxFileChooser;
import net.sf.anathema.initialization.GuiInitializer;
import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.library.logging.Logger;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.exception.ExceptionHandling;
import net.sf.anathema.platform.exception.ExtensibleExceptionHandler;
import net.sf.anathema.platform.frame.ApplicationFrame;
import net.sf.anathema.platform.fx.FxAcceleratorMap;
import net.sf.anathema.platform.initialization.EnvironmentFactory;

public class Anathema extends Application {

  private Environment environment;
  private ExtensibleExceptionHandler exceptionHandler;
  private FxUiEnvironment uiEnvironment;

  /*Called by the boot loader using reflection.*/
  @SuppressWarnings("UnusedDeclaration")
  public void startApplication() throws Exception {
    launch();
  }

  @Override
  public void init() throws Exception {
    Logger.getLogger(Anathema.class).info("Launching Anathema");
    this.exceptionHandler = new ExceptionHandling().create();
    this.environment = new EnvironmentFactory(exceptionHandler).create();
    this.uiEnvironment = new FxUiEnvironment();
  }

  @Override
  public void start(Stage stage) throws Exception {
    try {
      displayStatus("Initializing Environment...");
      uiEnvironment.setStage(stage);
      uiEnvironment.setFileChooser(new FxFileChooser(stage));
      displayStatus("Starting Platform...");
      ApplicationFrame applicationFrame = new GuiInitializer(stage, environment, uiEnvironment, exceptionHandler).initialize().getWindow();
      uiEnvironment.setAcceleratorMap(new FxAcceleratorMap(stage.getScene().getAccelerators()));
      displayStatus("Done.");
      applicationFrame.show();
    } catch (InitializationException e) {
      environment.handle(e);
    }
  }

  private void displayStatus(String message) {
    ProxySplashscreen.getInstance().displayStatusMessage(message);
  }
}