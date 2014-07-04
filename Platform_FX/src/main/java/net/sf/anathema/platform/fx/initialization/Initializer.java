package net.sf.anathema.platform.fx.initialization;

import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationFrameView;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.frame.ApplicationView;

public abstract class Initializer {

  private final Environment environment;

  public Initializer(Environment environment) throws InitializationException {
    this.environment = environment;
  }

  protected InitializedModelAndView initializeModelViewAndPresentation() throws InitializationException {
    configureExceptionHandling(environment);
    showVersion(environment);
    ApplicationModel anathemaModel = initModel(environment);
    ApplicationFrameView view = initView(environment, anathemaModel, environment);
    initPresentation(environment, anathemaModel, view);
    return new InitializedModelAndView(view, anathemaModel);
  }

  protected void configureExceptionHandling(Resources resources) {
    //nothing to do
  }

  protected void initPresentation(Environment environment, ApplicationModel anathemaModel, ApplicationView view) {
    AnathemaPresenter presenter = new AnathemaPresenter(anathemaModel, view, environment);
    presenter.initPresentation();
  }

  private ApplicationModel initModel(Environment environment) throws InitializationException {
    displayMessage("Creating Model...");
    AnathemaModelInitializer modelInitializer = new AnathemaModelInitializer();
    return modelInitializer.initializeModel(environment);
  }

  protected abstract void showVersion(Resources resources);

  protected abstract void displayMessage(String message);

  protected abstract ApplicationFrameView initView(Environment environment, ApplicationModel anathemaModel, ObjectFactory objectFactory);
}