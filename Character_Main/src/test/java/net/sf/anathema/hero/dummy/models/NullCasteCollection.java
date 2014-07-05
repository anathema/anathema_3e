package net.sf.anathema.hero.dummy.models;

import net.sf.anathema.hero.concept.model.concept.CasteCollection;
import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.individual.splat.SplatType;

public class NullCasteCollection implements CasteCollection {
  @Override
  public boolean containsCasteType(String casteTypeId) {
    return false;
  }

  @Override
  public CasteType[] getAllCasteTypes(SplatType template) {
	  return new CasteType[0];
  }


  @Override
  public CasteType[] getWithFavoredTrait(String id) {
	  return new CasteType[0];
  }

  @Override
  public CasteType getById(String casteTypeId) {
    return CasteType.NULL_CASTE_TYPE;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }
}
