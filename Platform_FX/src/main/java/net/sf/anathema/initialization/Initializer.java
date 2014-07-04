package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.environment.Environment;

public abstract class Initializer {

  private final Environment environment;

  public Initializer(Environment environment) throws InitializationException {
    this.environment = environment;
  }

  protected InitializedModelAndView initializeModelViewAndPresentation() throws InitializationException {
    configureExceptionHandling(environment);
    showVersion(environment);
    IApplicationModel anathemaModel = initModel(environment);
    ApplicationFrameView view = initView(environment, anathemaModel, environment);
    initPresentation(environment, anathemaModel, view);
    return new InitializedModelAndView(view, anathemaModel);
  }

  protected void configureExceptionHandling(Resources resources) {
    //nothing to do
  }

  protected void initPresentation(Environment environment, IApplicationModel anathemaModel, ApplicationView view) {
    AnathemaPresenter presenter = new AnathemaPresenter(anathemaModel, view, environment);
    presenter.initPresentation();
  }

  private IApplicationModel initModel(Environment environment) throws InitializationException {
    displayMessage("Creating Model...");
    AnathemaModelInitializer modelInitializer = new AnathemaModelInitializer();
    return modelInitializer.initializeModel(environment);
  }

  protected abstract void showVersion(Resources resources);

  protected abstract void displayMessage(String message);

  protected abstract ApplicationFrameView initView(Environment environment, IApplicationModel anathemaModel, ObjectFactory objectFactory);
}