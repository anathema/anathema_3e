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
  public static final String NATURAL = "Natural";
  
  private static final WeaponStats baseUnarmed = new WeaponStats() {
    {
      setName(new SimpleIdentifier("Unarmed"));
      addTag(Natural);
      addTag(Light);
      addTag(Bashing);
    }
  };
  
  private WeaponStats currentUnarmed = new WeaponStats() {
  	{
  		setName(baseUnarmed.getName());
  		copyTagsFromBase(baseUnarmed, this);
  	}
  };
  
  private static final WeaponStats baseSavage = new WeaponStats() {
    {
      setName(new SimpleIdentifier("Savage"));
      addTag(Natural);
      addTag(Light);
      addTag(Bashing);
    }
  };
  
  private WeaponStats currentSavage = new WeaponStats() {
  	{
  		setName(baseSavage.getName());
  		copyTagsFromBase(baseSavage, this);
  	}
  };
  
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
  	
  	copyTagsFromBase(baseUnarmed, currentUnarmed);
    for(UnarmedModificationProvider provider : providers.getProviders()) {
    	for (Identifier tag : new ArrayList<Identifier>(currentUnarmed.getTags())) {
    		if (provider.hasModificationOnUnarmed((WeaponTag)tag)) {
    			currentUnarmed.removeTag((IWeaponTag) tag);
    			currentUnarmed.addTag(provider.performModificationOnUnarmed((WeaponTag)tag));
    		}
    	}
    }
    stats.add(currentUnarmed);
    
    copyTagsFromBase(baseSavage, currentSavage);
    for(UnarmedModificationProvider provider : providers.getProviders()) {
    	for (Identifier tag : new ArrayList<Identifier>(currentSavage.getTags())) {
    		if (provider.hasModificationOnSavage((WeaponTag)tag)) {
    			currentSavage.removeTag((IWeaponTag) tag);
    			currentSavage.addTag(provider.performModificationOnSavage((WeaponTag)tag));
    		}
    	}
    }
    stats.add(currentSavage);
  	
    return stats;
  }
  
  private void copyTagsFromBase(WeaponStats base, WeaponStats copy) {
  	
  	for(Identifier tag : copy.getTags()) {
  		if (!base.getTags().contains(tag)) {
  			copy.removeTag((IWeaponTag)tag);
  		}
  	}
  	for(Identifier tag : base.getTags()) {
  		if (!copy.getTags().contains(tag)) {
  			copy.addTag((IWeaponTag)tag);
  		}
  	}
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