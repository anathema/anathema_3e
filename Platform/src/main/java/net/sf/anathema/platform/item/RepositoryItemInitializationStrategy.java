package net.sf.anathema.platform.item;

public interface RepositoryItemInitializationStrategy {
  String getItemPattern();

  String getMessageKey();

  boolean shouldInitializeData();

  void storeInRepository(String itemJSON);
}