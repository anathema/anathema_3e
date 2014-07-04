package net.sf.anathema.library.sort;

import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.resources.Resources;

import java.util.function.Function;

public class IdentifierToI18n<Id extends Identifier> implements Function<Id, String> {

  private Resources resources;

  public IdentifierToI18n(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String apply(Id id) {
    return resources.getString(id.getId());
  }
}
