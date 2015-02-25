package net.sf.anathema.hero.thaumaturgy.model;

import java.util.List;

import net.sf.anathema.hero.individual.change.FlavoredChangeListener;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.model.RemovableEntryModel;

public interface ThaumaturgyModel extends RemovableEntryModel<KnownRitual>, HeroModel {

  Identifier ID = new SimpleIdentifier("Thaumaturgy");

  List<KnownRitual> getKnownRituals();

  List<ThaumaturgyRitual> getCurrentRitualOptions();

  void setCurrentRitual(ThaumaturgyRitual option);

  void setCurrentDescription(String description);

  ThaumaturgyRitual getCurrentRitualOption();
  
  ThaumaturgyRitual findOptionByReference(RitualReference reference);

  void addChangeListener(FlavoredChangeListener listener);

  void resetCurrentRitual();

  void whenTypeChanges(ChangeListener changeListener);

  void whenCurrentOptionChanges(ChangeListener changeListener);

  boolean isEntryAllowed();
}