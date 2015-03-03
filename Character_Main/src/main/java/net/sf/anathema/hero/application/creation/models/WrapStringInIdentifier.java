package net.sf.anathema.hero.application.creation.models;

import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import com.google.common.base.Function;

public class WrapStringInIdentifier implements Function<String, Identifier> {
  @Override
  public Identifier apply(String input) {
    return new SimpleIdentifier(input);
  }
}