package net.sf.anathema.hero.intimacies.model;

import net.sf.anathema.hero.framework.library.removableentry.RemovableEntryModel;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface IntimaciesModel extends RemovableEntryModel<Intimacy>, HeroModel {

  Identifier ID = new SimpleIdentifier("Intimacies");

  void setCurrentName(String newValue);

  void setCurrentStrength(Strength newValue);

  void setCurrentOutlook(Outlook outlook);

  void setCurrentBond(Bond bond);

  void addChangeListener(FlavoredChangeListener listener);

  void addModelChangeListener(ChangeListener listener);

  boolean isCharacterExperienced();

  Strength[] getStrengths();

  Bond[] getBonds();

  Outlook[] getOutlooks();

  Strength getStrength();

  Outlook getOutlook();

  Bond getBond();
}