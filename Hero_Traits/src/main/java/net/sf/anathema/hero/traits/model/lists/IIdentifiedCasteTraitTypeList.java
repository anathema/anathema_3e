package net.sf.anathema.hero.traits.model.lists;

import net.sf.anathema.hero.elsewhere.concept.CasteType;
import net.sf.anathema.hero.traits.model.TraitType;

public interface IIdentifiedCasteTraitTypeList extends IdentifiedTraitTypeList {

  CasteType[] getTraitCasteTypes(TraitType type);
}