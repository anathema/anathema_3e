package net.sf.anathema.hero.equipment.model.natural;

import net.sf.anathema.equipment.character.UnarmedModificationProvider;

import java.util.ArrayList;
import java.util.List;

public class UnarmedModificationProviderCollection {
	private List<UnarmedModificationProvider> providers = new ArrayList<>();
	
	public void addProvider(UnarmedModificationProvider provider) {
		providers.add(provider);
	}
	
	public UnarmedModificationProvider[] getProviders() {
		return providers.toArray(new UnarmedModificationProvider[0]);
	}
}
