package net.sf.anathema.initialization;

import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.frame.ApplicationView;

public class InitializedModelAndView {
  public final ApplicationView view;
  public final ApplicationModel model;

  public InitializedModelAndView(ApplicationView view, ApplicationModel model) {
    this.view = view;
    this.model = model;
  }
}