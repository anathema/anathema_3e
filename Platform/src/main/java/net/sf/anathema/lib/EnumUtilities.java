package net.sf.anathema.lib;

public class EnumUtilities {

  public static boolean hasValue(Class<? extends Enum> enumeration, String value) {
    try {
      Enum.valueOf(enumeration, value);
      return true;
    } catch(Exception e) {
      return false;
    }
  }
}
