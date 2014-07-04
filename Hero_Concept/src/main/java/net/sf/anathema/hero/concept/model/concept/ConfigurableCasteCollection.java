package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.hero.concept.template.caste.CasteListTemplate;
import net.sf.anathema.hero.concept.template.caste.CasteTemplate;
import net.sf.anathema.hero.elsewhere.concept.CasteCollection;
import net.sf.anathema.hero.elsewhere.concept.CasteType;
import net.sf.anathema.hero.individual.splat.SplatType;

import java.util.ArrayList;
import java.util.List;

public class ConfigurableCasteCollection implements CasteCollection {
  private final List<CasteType> allTypes = new ArrayList<>();

  public ConfigurableCasteCollection(CasteListTemplate template) {
    for (CasteTemplate caste : template.castes) {
      allTypes.add(new ConfigurableCasteType(caste.id, caste.traits.toArray(new String[0])));
    }
  }

  @Override
  public CasteType getById(String casteTypeId) {
    for (CasteType type : allTypes) {
      if (type.getId().equals(casteTypeId)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No caste with found for id " + casteTypeId);
  }

  @Override
  public boolean isEmpty() {
    return allTypes.isEmpty();
  }

  @Override
  public boolean containsCasteType(String casteTypeId) {
    for (CasteType type : allTypes) {
      if (type.getId().equals(casteTypeId)) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public CasteType[] getWithFavoredTrait(String id) {
	  List<CasteType> castes = new ArrayList<>();
	  for (CasteType caste : getAllCasteTypes(null)) {
		  for (String favoredTrait : caste.getCasteTraitIds()) {
			  if (favoredTrait.equals(id)) {
				  castes.add(caste);
				  break;
			  }
		  }
	  }
	  return castes.toArray(new CasteType[0]);
  }

  @Override
  public CasteType[] getAllCasteTypes(SplatType template) {
    return allTypes.toArray(new CasteType[allTypes.size()]);
  }
}