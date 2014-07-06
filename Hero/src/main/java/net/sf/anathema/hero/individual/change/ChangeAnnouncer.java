package net.sf.anathema.hero.individual.change;

public interface ChangeAnnouncer {

  void addListener(FlavoredChangeListener listener);

  void announceChangeFlavor(ChangeFlavor flavor);

  void announceChange(HeroChange flavor);
}
