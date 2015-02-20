package net.sf.anathema.hero.equipment.model.natural;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.equipment.character.UnarmedModificationProvider;

public class UnarmedModificationProviderCollection {
	private List<UnarmedModificationProvider> providers = new ArrayList<>();
	
	public void addProvider(UnarmedModificationProvider provider) {
		providers.add(provider);
	}
	
	public UnarmedModificationProvider[] getProviders() {
		return providers.toArray(new UnarmedModificationProvider[0]);
	}
}
