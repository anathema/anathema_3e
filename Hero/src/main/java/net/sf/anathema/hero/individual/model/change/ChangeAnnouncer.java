package net.sf.anathema.hero.individual.model.change;

public interface ChangeAnnouncer {

  void addListener(FlavoredChangeListener listener);

  void announceChangeOf(ChangeFlavor flavor);
}
