package net.sf.anathema.hero.merits.model;

import java.util.Collection;
import java.util.List;

import net.sf.anathema.hero.individual.change.FlavoredChangeListener;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.model.RemovableEntryModel;

public interface MeritsModel extends RemovableEntryModel<Merit>, HeroModel {

  Identifier ID = new SimpleIdentifier("Merits");

  List<Merit> getMerits();

  boolean hasMeritsMatchingReference(MeritReference reference);

  List<Merit> getMeritsMatchingReference(MeritReference option);

  List<MeritOption> getCurrentMeritOptions();

  List<MeritOption> getCurrentMeritOptionsOfAllTypes();

  void setCurrentType(MeritCategory newValue);

  void setCurrentMeritOption(MeritOption option);

  void setCurrentDescription(String description);

  MeritCategory getCurrentType();

  MeritOption getCurrentMeritOption();

  List<Trait> getContingentTraits();

  void addChangeListener(FlavoredChangeListener listener);

  boolean isCharacterExperienced();

  void resetCurrentMerit();

  MeritOption findOptionByReference(MeritReference reference);

  void whenTypeChanges(ChangeListener changeListener);

  void whenCurrentOptionChanges(ChangeListener changeListener);

  Collection<String> getSuggestedDescriptions();
}