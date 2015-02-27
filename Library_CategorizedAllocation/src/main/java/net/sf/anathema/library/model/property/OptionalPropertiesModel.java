package net.sf.anathema.library.model.property;

import java.util.Collection;
import java.util.List;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.model.OptionalEntryReference;
import net.sf.anathema.library.model.RemovableEntryModel;
import net.sf.anathema.library.model.trait.OptionalTraitOption;

public interface OptionalPropertiesModel<
	C extends OptionalEntryCategory,
	O extends OptionalPropertyOption,
	T extends PossessedOptionalProperty<O>> extends RemovableEntryModel<T>, HeroModel {
	
	List<T> getPossessedEntries();
	
	List<O> getAllEntryOptions();
	
	List<O> getCurrentEntryOptions();
	
	O getSelectedEntryOption();
	
	void setSelectedEntryOption(O option);
	
	void selectFirstEntryOption();
	
	void whenSelectedOptionChanges(ChangeListener listener);
	
	O findOptionByReference(OptionalEntryReference reference);
	
	Collection<String> getSuggestedDescriptions();
	
	List<C> getAvailableCategories();
	
	C getCurrentCategory();
	
	void setCurrentCategory(C category);
	
	void whenCategoryChanges(ChangeListener listener);
	
	void setCurrentDescription(String text);
	
	void resetCurrentEntry();
	
	List<Trait> getContingentTraits();

	boolean hasCategories();
	
	boolean isEntryAllowed();
	
	boolean isRemovalAllowed(T entry);
}
