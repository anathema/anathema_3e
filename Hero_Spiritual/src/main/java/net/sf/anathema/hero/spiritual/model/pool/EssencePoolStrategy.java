package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.IdentifiedInteger;

public interface EssencePoolStrategy {

  int getFullPersonalPool();

  int getFullPeripheralPool();

  int getExtendedPersonalPool();

  int getExtendedPeripheralPool();

  int getStandardPersonalPool();

  int getStandardPeripheralPool();

  int getUnmodifiedPersonalPool();

  int getUnmodifiedPeripheralPool();

  int getOverdrivePool();

  IdentifiedInteger[] getComplexPools();

  int getAttunementExpenditures();

  void addPoolChangeListener(ChangeListener listener);
}