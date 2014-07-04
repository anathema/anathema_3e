package net.sf.anathema.hero.charms.display.magic;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.interaction.model.Tool;

import java.util.List;

public interface MagicLearnView {

  void setAvailableMagic(List magics);

  void setLearnedMagic(List magics);

  boolean hasMoreThanOneElementLearned();

  boolean hasAnyElementLearned();

  void addLearnedListListener(ChangeListener changeListener);

  Tool addAdditionalTool();

  Tool addMainTool();

  List getSelectedLearnedValues();

  List getSelectedAvailableValues();

  void addAvailableMagicSelectedListener(ChangeListener changeListener);

  void addLearnedMagicSelectedListener(ChangeListener changeListener);
}