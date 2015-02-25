package net.sf.anathema.hero.thaumaturgy.model;

import java.util.List;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.change.FlavoredChangeListener;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.RemovableEntryChangeAdapter;
import net.sf.anathema.hero.thaumaturgy.compiler.ThaumaturgyRitualCache;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.model.AbstractRemovableEntryModel;
import net.sf.anathema.library.model.RemovableEntryListener;

import org.jmock.example.announcer.Announcer;

public class ThaumaturgyModelImpl extends AbstractRemovableEntryModel<KnownRitual> implements ThaumaturgyModel {

  private final Announcer<ChangeListener> currentTypeChangeAnnouncer = Announcer.to(ChangeListener.class);
  private final Announcer<ChangeListener> currentMeritChangeAnnouncer = Announcer.to(ChangeListener.class);
  private ChangeAnnouncer change;
  private ThaumaturgyRitualCache ritualCache;
  private ThaumaturgyRitual currentRitual = new NullRitualOption();
  private String currentDescription = "";
  private Hero hero;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.hero = hero;
    this.ritualCache = environment.getDataSet(ThaumaturgyRitualCache.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void initializeListening(final ChangeAnnouncer announcer) {
    addModelChangeListener((RemovableEntryListener) new RemovableEntryChangeAdapter<>(announcer));
    change = announcer;
  }

  @Override
  public List<KnownRitual> getKnownRituals() {
    return getEntries();
  }

  @Override
  public List<ThaumaturgyRitual> getCurrentRitualOptions() {
    List<ThaumaturgyRitual> options = ritualCache.getAllRitualOptions();
    options.removeIf(item -> knowsRitual(item));
    return options;
  }

  @Override
  public void setCurrentRitual(ThaumaturgyRitual option) {
    if (option == null) {
      option = new NullRitualOption();
    }
    this.currentRitual = option;
    currentMeritChangeAnnouncer.announce().changeOccurred();
    fireEntryChanged();
  }

  @Override
  public void setCurrentDescription(String description) {
    this.currentDescription = description;
    fireEntryChanged();
  }

  @Override
  public ThaumaturgyRitual getCurrentRitualOption() {
    return currentRitual;
  }

  @Override
  public void addChangeListener(FlavoredChangeListener listener) {
    hero.getChangeAnnouncer().addListener(listener);
  }

  private boolean knowsRitual(ThaumaturgyRitual option) {
    for (KnownRitual ritual : getEntries()) {
      if (ritual.getBaseOption().equals(option)) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected KnownRitual createEntry() {
    KnownRitualImpl ritual = new KnownRitualImpl(currentRitual, currentDescription, hero);
    ritual.addCurrentValueListener(new TraitValueChangedListener(change, ritual));
    return ritual;
  }

  @Override
  public void resetCurrentRitual() {
    currentDescription = "";
    selectFirstRitualOption();
  }

  @Override
  public void whenTypeChanges(ChangeListener changeListener) {
    currentTypeChangeAnnouncer.addListener(changeListener);
  }

  @Override
  public void whenCurrentOptionChanges(ChangeListener changeListener) {
    currentMeritChangeAnnouncer.addListener(changeListener);
  }

  private void selectFirstRitualOption() {
    List<ThaumaturgyRitual> currentRitualOptions = getCurrentRitualOptions();
    if (currentRitualOptions.isEmpty()) {
      setCurrentRitual(new NullRitualOption());
      return;
    }
    setCurrentRitual(currentRitualOptions.get(0));
  }
  
  @Override
  public ThaumaturgyRitual findOptionByReference(RitualReference reference) {
    return ritualCache.getRitualByReference(reference);
  }

	@Override
	public boolean isEntryAllowed() {
		return true;
	}
}