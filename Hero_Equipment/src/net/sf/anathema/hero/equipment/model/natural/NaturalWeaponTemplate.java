package net.sf.anathema.hero.equipment.model.natural;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.equipment.character.UnarmedModificationProvider;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.IWeaponTag;
import net.sf.anathema.equipment.stats.WeaponTag;
import net.sf.anathema.equipment.stats.impl.WeaponStats;
import net.sf.anathema.equipment.template.IEquipmentTemplate;
import net.sf.anathema.equipment.template.ItemCost;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import static net.sf.anathema.equipment.stats.WeaponTag.Bashing;
import static net.sf.anathema.equipment.stats.WeaponTag.Light;
import static net.sf.anathema.equipment.stats.WeaponTag.Natural;

public class NaturalWeaponTemplate implements IEquipmentTemplate {

	private final UnarmedModificationProviderCollection providers;
  private static final String NATURAL = "Natural";
  
  public NaturalWeaponTemplate(UnarmedModificationProviderCollection providers) {
  	this.providers = providers;
  }

  @Override
  public String getDescription() {
    return NATURAL;
  }

  @Override
  public Collection<IEquipmentStats> getStatsList() {
  	List<IEquipmentStats> stats = newArrayList();
  	
  	WeaponStats baseUnarmed = new WeaponStats() {
      {
        setName(new SimpleIdentifier("Unarmed"));
        addTag(Natural);
        addTag(Light);
        addTag(Bashing);
      }
    };
    for(UnarmedModificationProvider provider : providers.getProviders()) {
    	for (Identifier tag : new ArrayList<Identifier>(baseUnarmed.getTags())) {
    		if (provider.hasModificationOnUnarmed((WeaponTag)tag)) {
    			baseUnarmed.removeTag((IWeaponTag) tag);
    			baseUnarmed.addTag(provider.performModificationOnUnarmed((WeaponTag)tag));
    		}
    	}
    }
    stats.add(baseUnarmed);
    
    WeaponStats baseSavage = new WeaponStats() {
      {
        setName(new SimpleIdentifier("Savage"));
        addTag(Natural);
        addTag(Light);
        addTag(Bashing);
      }
    };
    for(UnarmedModificationProvider provider : providers.getProviders()) {
    	for (Identifier tag : new ArrayList<Identifier>(baseSavage.getTags())) {
    		if (provider.hasModificationOnSavage((WeaponTag)tag)) {
    			baseSavage.removeTag((IWeaponTag) tag);
    			baseSavage.addTag(provider.performModificationOnSavage((WeaponTag)tag));
    		}
    	}
    }
    stats.add(baseSavage);
  	
    return stats;
  }

  @Override
  public String getName() {
    return NATURAL;
  }

  @Override
  public ItemCost getCost() {
    return null;
  }
}