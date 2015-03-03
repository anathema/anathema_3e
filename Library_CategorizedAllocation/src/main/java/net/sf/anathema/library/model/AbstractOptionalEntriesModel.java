package net.sf.anathema.library.model;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.RemovableEntryChangeAdapter;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.event.ChangeListener;

import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractOptionalEntriesModel<
	O extends OptionalEntryOption,
	T extends PossessedOptionalEntry<O>>
	extends AbstractRemovableEntryModel<T>
	implements OptionalEntriesModel<O, T> {
	
private final Map<OptionalEntryReference, Collection<String>> suggestions = new HashMap<>();
	
	private final Announcer<ChangeListener> currentCategoryChangeAnnouncer = Announcer.to(ChangeListener.class);
	private final Announcer<ChangeListener> currentOptionChangeAnnouncer = Announcer.to(ChangeListener.class);
	
	private final boolean hasCategories;
	protected Hero hero;
	
	protected OptionalEntryCategorySupplier categorySupplier;
	protected OptionalEntryOptionSupplier<O> optionSupplier;
	protected ChangeAnnouncer change;
	protected String currentDescription = "";
	protected OptionalEntryCategory currentCategory;
	protected O currentOption;
	
	protected AbstractOptionalEntriesModel(boolean hasCategories) {
		this.hasCategories = hasCategories;
	}
	
	@Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.hero = hero;
    categorySupplier = initCategorySupplier(environment);
    optionSupplier = initOptionSupplier(environment);

    if (hasCategories) {
    	currentCategory = getAvailableCategories().get(0);
    }
  }
	
	protected abstract OptionalEntryOptionSupplier<O> initOptionSupplier(HeroEnvironment environment);
	
	protected OptionalEntryCategorySupplier initCategorySupplier(HeroEnvironment environment) {
	  return new EmptyCategorySupplier();
	}
	
	@SuppressWarnings("unchecked")
  @Override
  public void initializeListening(final ChangeAnnouncer announcer) {
    addModelChangeListener((RemovableEntryListener) new RemovableEntryChangeAdapter<>(announcer));
    change = announcer;
  }
	
	@Override
	public boolean hasCategories() {
		return hasCategories;
	}
	
	@Override
	public void resetCurrentEntry() {
		currentDescription = "";
    selectFirstEntryOption();
	}
	
	public void selectFirstEntryOption() {
    List<O> currentOptions = getCurrentEntryOptions();
    if (currentOptions.isEmpty()) {
      setSelectedEntryOption(getNullOption());
      return;
    }
    setSelectedEntryOption(currentOptions.get(0));
  }
	
	@Override
  protected T createEntry() {
    return createPossessedEntry(getSelectedEntryOption(), currentDescription, hero);
  }
	
	protected abstract T createPossessedEntry(O option, String description, Hero hero);
	
	@Override
	public List<O> getAllEntryOptions() {
		return optionSupplier.getAllOptions();
	}
	
	@Override
	public List<O> getCurrentEntryOptions() {
	  List<O> options = hasCategories ? optionSupplier.getAllOptionsForCategory(currentCategory) :
      optionSupplier.getAllOptions();
    options.removeIf(item -> !isAllowedOption(item));
    return options;
	}
	
	@Override
	public List<O> getCurrentEntryOptionsFromAllCategories() {
	  List<O> options = optionSupplier.getAllOptions();
    options.removeIf(item -> !isAllowedOption(item));
    return options;
	}
	
	protected abstract boolean isAllowedOption(O option);
	
	

	@Override
	public O getSelectedEntryOption() {
		return currentOption;
	}
	
	@Override
	public void setSelectedEntryOption(O option) {
		if (option == null) {
      option = getNullOption();
    }
		if (option.equals(currentOption)) {
			return;
		}
    this.currentOption = option;
    currentOptionChangeAnnouncer.announce().changeOccurred();
    fireEntryChanged();
	}
	
	@Override
	public void whenSelectedOptionChanges(ChangeListener listener) {
		currentOptionChangeAnnouncer.addListener(listener);
	}
	
	@Override
  public O findOptionByReference(OptionalEntryReference reference) {
    return optionSupplier.getOptionByReference(reference);
  }
	
	@Override
  public void setCurrentDescription(String description) {
    this.currentDescription = description;
    fireEntryChanged();
  }
	

  public void addSuggestions(OptionalEntryReference merit, Collection<String> suggestionsForReference) {
    suggestions.put(merit, suggestionsForReference);
  }
	
	@Override
	public Collection<String> getSuggestedDescriptions() {
		return currentOption.getSuggestions();
	}
	
	protected boolean knowsTrait(O option) {
		return getEntries().stream().anyMatch(trait -> trait.getBaseOption().equals(option));
	}
	
	protected abstract O getNullOption();
	
	@Override
	public List<OptionalEntryCategory> getAvailableCategories() {
		return new ArrayList<>();
	}
	
	@Override
	public OptionalEntryCategory getCurrentCategory() {
		return currentCategory;
	}
	
	@Override
	public void setCurrentCategory(OptionalEntryCategory category) {
		if (category.equals(currentCategory)) {
			return;
		}
		currentCategory = category;
		currentCategoryChangeAnnouncer.announce().changeOccurred();
		fireEntryChanged();
	}
	
	@Override
	public void whenCategoryChanges(ChangeListener listener) {
		currentCategoryChangeAnnouncer.addListener(listener);
	}
	
	@Override
	public List<Trait> getContingentTraits() {
		return new ArrayList<>();
	}
	
	protected boolean hasDescription() {
		return !currentDescription.isEmpty();
	}
}
