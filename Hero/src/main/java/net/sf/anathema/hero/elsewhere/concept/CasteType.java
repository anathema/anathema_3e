package net.sf.anathema.hero.elsewhere.concept;

import net.sf.anathema.library.identifier.Identifier;

public interface CasteType extends Identifier {

  CasteType NULL_CASTE_TYPE = new CasteType() {
    @Override
    public String getId() {
      return null;
    }
  };
}