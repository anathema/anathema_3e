package net.sf.anathema.hero.individual.change;

public interface ChangeAnnouncer {

  void addListener(FlavoredChangeListener listener);

  void announceChangeOf(ChangeFlavor flavor);
}
