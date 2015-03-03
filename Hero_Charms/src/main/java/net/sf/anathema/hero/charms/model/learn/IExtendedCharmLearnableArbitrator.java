package net.sf.anathema.hero.charms.model.learn;


public interface IExtendedCharmLearnableArbitrator extends CharmLearnArbitrator, CharmLearnableArbitrator {

  void addCharmLearnListener(ICharmLearnListener listener);
}