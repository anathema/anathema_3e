package net.sf.anathema.magic.description.module;

import net.sf.anathema.magic.description.model.MagicDescription;
import net.sf.anathema.magic.description.model.Paragraphs;

public class DirectMagicDescription implements MagicDescription {
  private String description;

  public DirectMagicDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean isEmpty() {
    return description == null || description.isEmpty();
  }

  @Override
  public Paragraphs getParagraphs() {
    return isEmpty() ? new Paragraphs() : splitInParagraphs();
  }

  private Paragraphs splitInParagraphs() {
    String[] split = description.split("\\r?\\n");
    return new Paragraphs(split);
  }
}
