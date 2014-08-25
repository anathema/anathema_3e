package net.sf.anathema.hero.charms.evocations.json;

import java.util.ArrayList;
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
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.template.CharmListTemplate;
import net.sf.anathema.charm.template.CharmTemplate;
import net.sf.anathema.charm.template.evocations.EvocationArtifactTemplate;
import net.sf.anathema.charm.template.evocations.EvocationTier;
import net.sf.anathema.charm.template.prerequisite.SimpleCharmPrerequisiteTemplate;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;
import net.sf.anathema.hero.charms.compiler.json.CharmImpl;
import net.sf.anathema.hero.charms.evocations.utilities.EvocationUtilities;

public class EvocationsBuilder {
  private List<EvocationArtifactTemplate> evocationCascades = new ArrayList<>();
  private Map<String, CharmTemplate> charmTemplates = new HashMap<>();

  public void addTemplate(EvocationArtifactTemplate listTemplate) {
  	if (listTemplate.category.equals("Evocations")) {
  		evocationCascades.add(listTemplate);
  	}
  }

  public void apply(CharmCacheImpl cache) {
    evocationCascades.stream().forEach(evocation -> addEvocation(evocation, cache));
  }
  
  public void addEvocation(EvocationArtifactTemplate evocation, CharmCacheImpl cache) {
  	Stream<Charm> charmsForEvocation = cache.getCharms(new CategoryReference(evocation.category)).stream().filter(charm ->
  		charm.getTreeReference().name.text.equals(evocation.tree));

  	charmsForEvocation.forEach(charm -> applyTierPrerequisites(charm, evocation));
  	applyInnateProperties(evocation, cache);
  }
  
  private void applyInnateProperties(EvocationArtifactTemplate template, CharmCacheImpl cache) {
  	createInnateCharms(template, template.innateOnSapphire, EvocationTier.Sapphire, cache);
  	createInnateCharms(template, template.innateOnAdamant, EvocationTier.Adamant, cache);
  }
  
  private void createInnateCharms(EvocationArtifactTemplate template, List<String> charmIds, EvocationTier innateAtTier, CharmCacheImpl cache) {
  	charmIds.forEach(id -> {
  		CharmName newCharmName = new CharmName(id + ".Innate");
  		CharmTemplate newCharmTemplate = charmTemplates.get(id).clone();
  		newCharmTemplate.keywords.remove(EvocationTier.Emerald.toString());
  		newCharmTemplate.keywords.remove(EvocationTier.Sapphire.toString());
  		newCharmTemplate.keywords.add(innateAtTier.toString());
  		newCharmTemplate.keywords.add("Innate");
  		String predecessor = id;
  		newCharmTemplate.prerequisites.clear();
  		newCharmTemplate.prerequisites.add(new SimpleCharmPrerequisiteTemplate(predecessor));
  		
      CharmImpl newCharm = new CharmImpl(new CategoryReference(template.category), 
      		new TreeName(template.tree),
      		newCharmName,
      		newCharmTemplate);
      setEvocationPrerequisite(newCharm, innateAtTier, 1);
      newCharm.addCharmPrerequisite(new SimpleCharmPrerequisite(cache.getCharmById(new CharmName(predecessor))));
      
  		cache.addCharm(newCharm);
  	});
  }
  
  private void applyTierPrerequisites(Charm charm, EvocationArtifactTemplate template) {
  	if (EvocationUtilities.getTier(charm) == EvocationTier.Sapphire && needsEvocationPrerequisites(charm, EvocationTier.Sapphire, template.emeraldRequiredForSapphire)) {
			int emeraldRequired = template.emeraldRequiredForSapphire - countPriorTierAncestors(charm, EvocationTier.Sapphire);
			if (oldParentsNotRequired(charm, EvocationTier.Emerald, template.emeraldRequiredForSapphire )) {
				clearOldPrerequisites(charm);
			}
			setEvocationPrerequisite(charm, EvocationTier.Emerald, emeraldRequired);
		}
		if (EvocationUtilities.getTier(charm) == EvocationTier.Adamant && needsEvocationPrerequisites(charm, EvocationTier.Adamant, template.sapphireRequiredForAdamant)) {
			if (oldParentsNotRequired(charm, EvocationTier.Sapphire, template.sapphireRequiredForAdamant)) {
				clearOldPrerequisites(charm);
			}
			setEvocationPrerequisite(charm, EvocationTier.Sapphire, template.sapphireRequiredForAdamant -
					countPriorTierAncestors(charm, EvocationTier.Adamant));
		}
  }
  
  private boolean oldParentsNotRequired(Charm rootCharm, EvocationTier target, int count) {
  	return countPriorTierAncestors(rootCharm, target) >= count;
  }
  
  private boolean needsEvocationPrerequisites(Charm rootCharm, EvocationTier target, int count) {
  	return hasPreviousTierParents(rootCharm, target) && countPriorTierAncestors(rootCharm, target) < count;
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
					if (EvocationUtilities.getTier(parentCharm).compareTo(target) < 0) {
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
  
  private int countPriorTierAncestors(Charm rootCharm, EvocationTier target) {
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
  					if (EvocationUtilities.getTier(parent).compareTo(target) <= 0) {
  						charmQueue.add(parent);
  						if (EvocationUtilities.getTier(parent).compareTo(target) < 0) {
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

	public void addCharmTemplates(CharmListTemplate listTemplate) {
		charmTemplates.putAll(listTemplate.charms);
	}
}
