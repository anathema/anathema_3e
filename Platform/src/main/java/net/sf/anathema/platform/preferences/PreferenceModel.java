package net.sf.anathema.platform.preferences;

public interface PreferenceModel extends DirtyModel {
  void serializeTo(PreferencePto pto);

  void initializeFrom(PreferencePto pto);
}