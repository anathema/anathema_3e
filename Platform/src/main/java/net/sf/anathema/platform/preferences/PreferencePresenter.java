package net.sf.anathema.platform.preferences;

import net.sf.anathema.platform.environment.Environment;

public interface PreferencePresenter {
  Class getViewClass();

  Class getModelClass();

  String getTitle();

  void useModel(PreferenceModel preferenceModel);

  void useView(PreferenceView view);

  void initialize();

  void useEnvironment(Environment environment);
}
