package net.sf.anathema.hero.magic.display.magic;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.interaction.model.Tool;

import java.util.List;

public interface MagicLearnView {

  void setAvailableMagic(List magics);

  void setLearnedMagic(List magics);

  Tool addMainTool();

  List getSelectedLearnedValues();

  List getSelectedAvailableValues();

  void addAvailableMagicSelectedListener(ChangeListener changeListener);

  void addLearnedMagicSelectedListener(ChangeListener changeListener);
}