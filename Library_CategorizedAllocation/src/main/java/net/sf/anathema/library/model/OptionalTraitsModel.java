package net.sf.anathema.library.model;

import java.util.Collection;
import java.util.List;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.event.ChangeListener;

public interface OptionalTraitsModel<
	C extends OptionalTraitCategory,
	O extends OptionalTraitOption,
	T extends KnownOptionalTrait<O>> extends RemovableEntryModel<T>, HeroModel {
	
	List<T> getKnownTraits();
	
	List<O> getAllTraitOptions();
	
	List<O> getCurrentTraitOptions();
	
	O getSelectedTraitOption();
	
	void setSelectedTraitOption(O option);
	
	void selectFirstOption();
	
	void whenSelectedOptionChanges(ChangeListener listener);
	
	O findOptionByReference(OptionalTraitReference reference);
	
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
}
