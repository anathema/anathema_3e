package net.sf.anathema.hero.spells.display.presenter;

public interface MagicViewListener {

  void removeMagicRequested(Object[] removedMagic);

  void addMagicRequested(Object[] addedMagic);
}