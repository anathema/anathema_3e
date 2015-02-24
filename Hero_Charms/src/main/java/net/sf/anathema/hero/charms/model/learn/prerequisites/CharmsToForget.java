package net.sf.anathema.hero.charms.model.learn.prerequisites;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.charms.model.learn.CharmLearnArbitrator;

import java.util.Set;

public class CharmsToForget {

  private Charm charm;
  private CompositeLearnWorker worker;

  public CharmsToForget(Charm charm, CharmLearnArbitrator worker) {
    this.charm = charm;
    this.worker = new CompositeLearnWorker(worker);
  }

  public Set<Charm> getLearnFollowUpCharms() {
    charm.forEachChild(this::addCharmsToForget);
    return worker.getForgottenCharms();
  }

  private void addCharmsToForget(Charm charm) {
    if (isCharmPrerequisiteListFulfilled(charm)) {
      return;
    }
    worker.forget(charm);
    charm.forEachChild(this::addCharmsToForget);
  }

  private boolean isCharmPrerequisiteListFulfilled(Charm charm) {
    AllSatisfied allSatisfied = new AllSatisfied(worker, CharmPrerequisite::isSpecific);
    charm.getPrerequisites().forEachCharmPrerequisite(allSatisfied);
    return allSatisfied.isFulfilled;
  }
}