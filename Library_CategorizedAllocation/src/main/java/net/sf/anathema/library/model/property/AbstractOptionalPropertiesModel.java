package net.sf.anathema.library.model.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.RemovableEntryChangeAdapter;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.model.AbstractRemovableEntryModel;
import net.sf.anathema.library.model.OptionalEntryCache;
import net.sf.anathema.library.model.OptionalEntryReference;
import net.sf.anathema.library.model.RemovableEntryListener;

import org.jmock.example.announcer.Announcer;

public abstract class AbstractOptionalPropertiesModel<
	C extends OptionalEntryCategory,
	O extends OptionalPropertyOption,
	T extends PossessedOptionalProperty<O>>
	extends AbstractRemovableEntryModel<T>
	implements OptionalPropertiesModel<C, O, T> {
	
  private final Map<OptionalEntryReference, Collection<String>> suggestions = new HashMap<>();
	
	private final Announcer<ChangeListener> currentCategoryChangeAnnouncer = Announcer.to(ChangeListener.class);
	private final Announcer<ChangeListener> currentOptionChangeAnnouncer = Announcer.to(ChangeListener.class);
	
	private final boolean hasCategories;
	protected Hero hero;
	protected OptionalEntryCache<C, O> cache;
	protected ChangeAnnouncer change;
	protected String currentDescription = "";
	protected C currentCategory;
	protected O currentOption;
	
	protected AbstractOptionalPropertiesModel(boolean hasCategories) {
		this.hasCategories = hasCategories;
	}
	
	@Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.hero = hero;
    cache = initCache(environment);

    if (hasCategories) {
    	currentCategory = getAvailableCategories().get(0);
    }
  }
	
	protected abstract OptionalEntryCache<C, O> initCache(HeroEnvironment environment);
	
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
	public List<T> getPossessedEntries() {
		return getEntries();
	}
	
	@Override
  protected T createEntry() {
    return createPossessedEntry(getSelectedEntryOption(), currentDescription, hero);
  }
	
	protected abstract T createPossessedEntry(O option, String description, Hero hero);
	
	@Override
	public List<O> getAllEntryOptions() {
		return cache.getAllOptions();
	}
	
	@Override
	public List<O> getCurrentEntryOptions() {
		List<O> options = hasCategories ? cache.getAllOptionsForCategory(currentCategory) :
			cache.getAllOptions();
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
    return cache.getOptionByReference(reference);
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
		return getPossessedEntries().stream().anyMatch(trait -> trait.getBaseOption().equals(option));
	}
	
	protected abstract O getNullOption();
	
	@Override
	public List<C> getAvailableCategories() {
		return new ArrayList<>();
	}
	
	@Override
	public C getCurrentCategory() {
		return currentCategory;
	}
	
	@Override
	public void setCurrentCategory(C category) {
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
}
