package net.sf.anathema;

import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationFrameView;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.initialization.InitializedModelAndView;
import net.sf.anathema.platform.fx.initialization.Initializer;
import net.sf.anathema.view.NullMainView;


public class TestInitializer extends Initializer {

  public static TestInitializer Create() throws InitializationException {
    return new TestInitializer(new DummyEnvironment());
  }

  private Environment environment;

  private TestInitializer(Environment environment) throws InitializationException {
    super(environment);
    this.environment = environment;
  }

  public ApplicationModel initialize() {
    InitializedModelAndView dao = initializeModelViewAndPresentation();
    return dao.model;
  }

  @Override
  protected void showVersion(Resources resources) {
    //nothing to do
  }

  @Override
  protected void displayMessage(String message) {
    //nothing to do
  }

  @Override
  protected ApplicationFrameView initView(Environment environment, ApplicationModel anathemaModel,
                                          ObjectFactory objectFactory) {
    return new NullMainView();
  }

  public Environment getEnvironment() {
    return environment;
  }
}