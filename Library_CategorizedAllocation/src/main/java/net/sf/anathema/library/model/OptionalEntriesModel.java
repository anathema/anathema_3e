package net.sf.anathema.library.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.event.ChangeListener;

import java.util.Collection;
import java.util.List;

public interface OptionalEntriesModel<
	O extends OptionalEntryOption,
	T extends PossessedOptionalEntry<O>> extends RemovableEntryModel<T>, HeroModel {
	
	List<O> getAllEntryOptions();
	
	List<O> getCurrentEntryOptions();
	
	List<O> getCurrentEntryOptionsFromAllCategories();
	
	O getSelectedEntryOption();
	
	void setSelectedEntryOption(O option);
	
	void selectFirstEntryOption();
	
	void whenSelectedOptionChanges(ChangeListener listener);
	
	O findOptionByReference(OptionalEntryReference reference);
	
	Collection<String> getSuggestedDescriptions();
	
	List<OptionalEntryCategory> getAvailableCategories();
	
	OptionalEntryCategory getCurrentCategory();
	
	void setCurrentCategory(OptionalEntryCategory category);
	
	void whenCategoryChanges(ChangeListener listener);
	
	void setCurrentDescription(String text);
	
	void resetCurrentEntry();
	
	List<Trait> getContingentTraits();

	boolean hasCategories();
	
	boolean isEntryAllowed();
	
	boolean isRemovalAllowed(T entry);
}
