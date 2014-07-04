package net.sf.anathema.library.preferences;

public interface Preferences {
  String PREFERENCE_NOT_SET = null;
  
  String getPreference(String key);
}
