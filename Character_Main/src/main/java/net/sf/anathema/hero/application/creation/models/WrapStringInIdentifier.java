package net.sf.anathema.hero.application.creation.models;

import com.google.common.base.Function;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class WrapStringInIdentifier implements Function<String, Identifier> {
  @Override
  public Identifier apply(String input) {
    return new SimpleIdentifier(input);
  }
}