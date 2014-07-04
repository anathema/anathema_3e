package net.sf.anathema.library.sort;

import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.resources.Resources;

public class I18nedIdentificateSorter<T extends Identifier> extends StringSorter<T> {

  public I18nedIdentificateSorter(Resources resources) {
    super(new IdentifierToI18n<>(resources));
  }
}