package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface EssencePoolModel {

  Identifier ID = new SimpleIdentifier("EssencePool");

  String getPersonalPool();

  int getPersonalPoolValue();

  String getPeripheralPool();

  int getPeripheralPoolValue();

  String getAttunedPool();

  int getAttunedPoolValue();

  boolean isEssenceUser();

  boolean hasPeripheralPool();

  void addPoolChangeListener(ChangeListener listener);

  Iterable<IEssencePoolModifier> getEssencePoolModifiers();

  void addEssencePoolModifier(IEssencePoolModifier modifier);
}