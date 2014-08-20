package net.sf.anathema.hero.charms.evocations.json;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
import net.sf.anathema.charm.template.evocations.EvocationArtifactTemplate;
import net.sf.anathema.charm.template.evocations.EvocationTier;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;
import net.sf.anathema.hero.charms.compiler.json.CharmImpl;

public class EvocationsBuilder {
  private List<EvocationArtifactTemplate> evocationCascades = new ArrayList<>();

  public void addTemplate(EvocationArtifactTemplate listTemplate) {
  	if (listTemplate.category.equals("Evocations")) {
  		evocationCascades.add(listTemplate);
  	}
  }

  public void apply(CharmCacheImpl cache) {
    evocationCascades.stream().forEach(evocation -> addEvocation(evocation, cache));
  }
  
  public void addEvocation(EvocationArtifactTemplate evocation, CharmCache cache) {
  	Stream<Charm> charmsForEvocation = cache.getCharms(new CategoryReference(evocation.category)).stream().filter(charm ->
  		charm.getTreeReference().name.text.equals(evocation.tree));

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
				}

				@Override
				public void visit(AttributeKnownCharmPrerequisite prerequisite) {
				}

				@Override
				public void visit(DirectGroupCharmPrerequisite prerequisite) {
				}

				@Override
				public void visit(SimpleCharmPrerequisite prerequisite) {
					Charm parentCharm = prerequisite.getParentCharm();
					if (getTier(parentCharm).compareTo(target) < 0) {
						hasImmediatePreviousTierParents[0] = true;
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
