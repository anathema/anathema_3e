package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.text.ITextualDescription;

import java.util.Collection;
import java.util.List;

public interface Combo extends Cloneable {

  void addComboModelListener(ChangeListener listener);

  void removeCharms(Collection<Charm> charm);

  @SuppressWarnings("CloneDoesntDeclareCloneNotSupportedException")
  Combo clone();

  void clear();

  ITextualDescription getName();

  ITextualDescription getDescription();

  List<Charm> getCharms();

  List<Charm> getCreationCharms();

  List<Charm> getExperiencedCharms();

  boolean contains(Charm charm);

  Integer getId();

  void setId(Integer id);

  void getValuesFrom(Combo combo);

  void addCharm(Charm charm, boolean experienced);
}