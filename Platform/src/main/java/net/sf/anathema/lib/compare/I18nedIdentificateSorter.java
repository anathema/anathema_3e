package net.sf.anathema.lib.compare;

import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.library.resources.Resources;

public class I18nedIdentificateSorter<T extends Identifier> extends StringSorter<T> {

  public I18nedIdentificateSorter(Resources resources) {
    super(new IdentifierToI18n<>(resources));
  }
}