package net.sf.anathema.platform.item;

public interface RepositoryConfiguration {

  String getMainFileName();

  boolean isItemSavedToSingleFile();

  String getFileExtension();

  String getFolderName();
}