package net.sf.anathema.hero.charms.compiler;

import static java.util.Collections.singletonList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Stream;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.AnyOneTraitCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.AttributeKnownCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.DirectGroupCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.EvocationTierPrerequisite;
import net.sf.anathema.charm.data.prerequisite.PrerequisiteVisitor;
import net.sf.anathema.charm.data.prerequisite.SimpleCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.SpecificGroupCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.TraitGroupCharmPrerequisite;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.template.evocations.EvocationArtifactTemplate;
import net.sf.anathema.charm.template.evocations.EvocationTier;
import net.sf.anathema.hero.charms.compiler.json.CharmImpl;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.library.collection.MultiEntryMap;

public class CharmCacheImpl implements CharmCache {

  private MultiEntryMap<CategoryReference, Charm> charmsByCategory = new MultiEntryMap<>();
  private Map<CategoryReference, List<ISpecialCharm>> specialCharmsByCategory = new HashMap<>();
  private Map<CharmName, Charm> charmsById = new HashMap<>();

  @Override
  public Charm getCharmById(CharmName charmName) {
    return charmsById.get(charmName);
  }

  @Override
  public List<Charm> getCharms(CategoryReference type) {
    if (!charmsByCategory.containsKey(type)) {
      return Collections.emptyList();
    }
    List<Charm> charmList = charmsByCategory.get(type);
    return new ArrayList<>(charmList);
  }

  @Override
  public List<CategoryReference> getAllCategories() {
    return new ArrayList<>(charmsByCategory.keySet());
  }

  @Override
  public List<ISpecialCharm> getSpecialCharms(CategoryReference type) {
    if (specialCharmsByCategory.containsKey(type)) {
      List<ISpecialCharm> specials = specialCharmsByCategory.get(type);
      return new ArrayList<>(specials);
    }
    return Collections.emptyList();
  }

  public Iterable<Charm> getCharms() {
    List<Charm> allCharms = new ArrayList<>();
    for (CategoryReference type : charmsByCategory.keySet()) {
      for (Charm charm : charmsByCategory.get(type)) {
        allCharms.add(charm);
      }
    }
    return allCharms;
  }

  public void addCharm(Charm charm) {
    charmsByCategory.replace(charm.getTreeReference().category, charm, charm);
    charmsById.put(charm.getName(), charm);
  }

  public void addSpecial(CategoryReference type, List<ISpecialCharm> specialCharms) {
    if (specialCharms == null) {
      return;
    }
    List<ISpecialCharm> cachedList = getCachedSpecialCharmList(type);
    cachedList.addAll(specialCharms);
  }

  private List<ISpecialCharm> getCachedSpecialCharmList(CategoryReference type) {
    if (!specialCharmsByCategory.containsKey(type)) {
      specialCharmsByCategory.put(type, new ArrayList<>());
    }
    return specialCharmsByCategory.get(type);
  }

  public void addSpecial(ISpecialCharm specialCharm) {
    Charm charm = getCharmById(specialCharm.getCharmName());
    addSpecial(charm.getTreeReference().category, singletonList(specialCharm));
  }
  
  public void addEvocation(EvocationArtifactTemplate evocation) {
  	Stream<Charm> charmsForEvocation = charmsByCategory.get(new CategoryReference(evocation.category)).stream().filter(charm ->
  		charm.getTreeReference().name.text.equals(evocation.tree));
  	// no clone capability?
  	Stream<Charm> charmsForCounting = charmsByCategory.get(new CategoryReference(evocation.category)).stream().filter(charm ->
			charm.getTreeReference().name.text.equals(evocation.tree));
  	Map<EvocationTier, Integer> charmsInCascade = new HashMap<>();

  	countEvocationsInCascade(charmsInCascade, charmsForCounting);
  	charmsForEvocation.forEach(charm -> {
  		if (getTier(charm) == EvocationTier.Sapphire && needsEvocationPrerequisites(charm, EvocationTier.Sapphire, evocation.emeraldRequiredForSapphire)) {
  			if (oldParentsNotRequired(charm, EvocationTier.Emerald, evocation.emeraldRequiredForSapphire )) {
  				clearOldPrerequisites(charm);
  			}
  			setEvocationPrerequisite(charm, EvocationTier.Emerald, evocation.emeraldRequiredForSapphire);
  		}
  		if (getTier(charm) == EvocationTier.Adamant && needsEvocationPrerequisites(charm, EvocationTier.Adamant, evocation.sapphireRequiredForAdamant)) {
  			if (oldParentsNotRequired(charm, EvocationTier.Sapphire, evocation.sapphireRequiredForAdamant)) {
  				clearOldPrerequisites(charm);
  			}
  			setEvocationPrerequisite(charm, EvocationTier.Sapphire, evocation.sapphireRequiredForAdamant);
  		}
  	});
  }
  
  private EvocationTier getTier(Charm charm) {
  	if (charm.hasAttribute(EvocationTier.Sapphire)) {
  		return EvocationTier.Sapphire;
  	}
  	if (charm.hasAttribute(EvocationTier.Adamant)) {
  		return EvocationTier.Adamant;
  	}
  	return EvocationTier.Emerald;
  }
  
  private boolean oldParentsNotRequired(Charm rootCharm, EvocationTier target, int count) {
  	return countAncestorsOfTier(rootCharm, target) >= count;
  }
  
  private void countEvocationsInCascade(Map<EvocationTier, Integer> counts, Stream<Charm> charms) {
  	for (EvocationTier tier : EvocationTier.values()) {
  		counts.put(tier, 0);
  	}
  	charms.forEach(charm -> {
  		EvocationTier tier = getTier(charm);
  		counts.put(tier, counts.get(tier) + 1);
  	});
  }
  
  private boolean needsEvocationPrerequisites(Charm rootCharm, EvocationTier target, int count) {
  	return hasPreviousTierParents(rootCharm, target) && countAncestorsOfTier(rootCharm, target) < count;
  }
  
  private boolean hasPreviousTierParents(Charm charm, EvocationTier target) {
  	boolean[] hasImmediatePreviousTierParents = new boolean[1];
  	hasImmediatePreviousTierParents[0] = false;
  	
  	for (CharmPrerequisite prerequisite : charm.getPrerequisites().getCharmPrerequisites()) {
  		prerequisite.accept(new PrerequisiteVisitor() {

				@Override
				public void visit(AnyOneTraitCharmPrerequisite prerequisite) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void visit(AttributeKnownCharmPrerequisite prerequisite) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void visit(DirectGroupCharmPrerequisite prerequisite) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void visit(SimpleCharmPrerequisite prerequisite) {
					Charm parentCharm = prerequisite.getParentCharm();
					if (parentCharm == null) {
						return;
					}
					if (getTier(parentCharm).compareTo(target) < 0) {
						hasImmediatePreviousTierParents[0] = true;
					}
				}

				@Override
				public void visit(SpecificGroupCharmPrerequisite prerequisite) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void visit(TraitGroupCharmPrerequisite prerequisite) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void visit(EvocationTierPrerequisite prerequisite) {
					// TODO Auto-generated method stub
					
				}
  			
  		});
  	}
  	return hasImmediatePreviousTierParents[0];
  }
  
  private int countAncestorsOfTier(Charm rootCharm, EvocationTier target) {
  	int[] ancestorsOfTier = new int[1];
  	ancestorsOfTier[0] = 0;
  	Queue<Charm> charmQueue = new LinkedList<>();
  	charmQueue.add(rootCharm);
  	while (!charmQueue.isEmpty()) {
  		Charm charm = charmQueue.poll();
  		for (CharmPrerequisite prerequisite : charm.getPrerequisites().getCharmPrerequisites()) {
    		prerequisite.accept(new PrerequisiteVisitor() {

  				@Override
  				public void visit(AnyOneTraitCharmPrerequisite prerequisite) {
  				}

  				@Override
  				public void visit(AttributeKnownCharmPrerequisite prerequisite) {
  				}

  				@Override
  				public void visit(DirectGroupCharmPrerequisite prerequisite) {
  				}

  				@Override
  				public void visit(SimpleCharmPrerequisite prerequisite) {
  					if (prerequisite.getParentCharm() == null) {
  						// TODO: Bizarre case; investigate
  						return;
  					}
  					Charm parent = prerequisite.getParentCharm();
  					if (getTier(parent).compareTo(target) <= 0) {
  						charmQueue.add(parent);
  						if (getTier(parent).compareTo(target) < 0) {
  							ancestorsOfTier[0]++;
  						}
  					}
  				}

  				@Override
  				public void visit(SpecificGroupCharmPrerequisite prerequisite) {
  				}

  				@Override
  				public void visit(TraitGroupCharmPrerequisite prerequisite) {
  				}

  				@Override
  				public void visit(EvocationTierPrerequisite prerequisite) {
  				}
    			
    		});
    	}
  	}
  	return ancestorsOfTier[0];
  }
  
  private void setEvocationPrerequisite(Charm charm, EvocationTier target, int quantity) {
  	CharmPrerequisite prerequisite = new EvocationTierPrerequisite(charm.getTreeReference(),
  			target, quantity);
  	((CharmImpl)charm).addCharmPrerequisite(prerequisite);
  }
  
  private void clearOldPrerequisites(Charm charm) {
  	((CharmImpl)charm).clearPrerequisites();
  }
}