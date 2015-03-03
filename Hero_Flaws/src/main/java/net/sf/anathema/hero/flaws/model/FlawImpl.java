package net.sf.anathema.hero.flaws.model;

import net.sf.anathema.library.model.property.AbstractPossessedProperty;

public class FlawImpl 
	extends AbstractPossessedProperty<FlawOption>
  implements Flaw {

  public FlawImpl(FlawOption type, String description, boolean isLearnedAtCreation) {
    super(type, description, isLearnedAtCreation);
  }
}