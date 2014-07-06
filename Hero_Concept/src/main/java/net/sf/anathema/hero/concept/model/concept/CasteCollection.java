package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.hero.individual.splat.SplatType;

import java.util.Collection;

public interface CasteCollection {

  boolean containsCasteType(String casteTypeId);

  Collection<CasteType> getAllCasteTypes(SplatType template);

  CasteType getById(String casteTypeId);

  boolean isEmpty();
}