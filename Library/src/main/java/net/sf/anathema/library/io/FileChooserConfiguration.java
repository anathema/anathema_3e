package net.sf.anathema.library.io;

public class FileChooserConfiguration {
  public final FileExtension extension;
  public final String nameSuggestion;

  public FileChooserConfiguration(FileExtension extension, String nameSuggestion) {
    this.extension = extension;
    this.nameSuggestion = nameSuggestion;
  }
}