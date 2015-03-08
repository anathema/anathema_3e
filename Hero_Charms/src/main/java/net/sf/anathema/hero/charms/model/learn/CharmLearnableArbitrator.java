package net.sf.anathema.hero.charms.model.learn;


import net.sf.anathema.magic.data.Charm;

public interface CharmLearnableArbitrator {

  boolean isLearnable(Charm charm);
}