package net.sf.anathema.lib.dummy;

import net.sf.anathema.library.resources.Resources;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class DummyResources implements Resources {
  private final Map<String, String> stringMap = new HashMap<>();

  @Override
  public boolean supportsKey(String key) {
    return stringMap.containsKey(key);
  }

  public void putString(String key, String value) {
    stringMap.put(key, value);
  }

  @Override
  public String getString(String key, Object... arguments) {
    if (!stringMap.containsKey(key)) {
      return "##" + key + "##";
    }
    if (arguments.length == 0) {
      return stringMap.get(key);
    }
    return MessageFormat.format(stringMap.get(key), arguments);
  }
}