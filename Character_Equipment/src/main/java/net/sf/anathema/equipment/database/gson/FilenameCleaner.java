package net.sf.anathema.equipment.database.gson;

public class FilenameCleaner {
  public static String clean(String input) {
    return input.replaceAll("\\W", "_");
  }
}
