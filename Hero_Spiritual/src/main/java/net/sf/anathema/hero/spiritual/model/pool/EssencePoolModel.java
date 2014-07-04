package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.library.event.ChangeListener;

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