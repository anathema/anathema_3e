package net.sf.anathema.hero.traits.dummy;

import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class DummyCasteType extends SimpleIdentifier implements CasteType {

  public DummyCasteType() {
    this("DummyCaste");
  }

  public DummyCasteType(String id) {
    super(id);
  }
}