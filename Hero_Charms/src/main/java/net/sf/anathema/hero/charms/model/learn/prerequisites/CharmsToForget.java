package net.sf.anathema.hero.charms.model.learn.prerequisites;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.prerequisite.DirectCharmPrerequisite;

import java.util.Set;

public class CharmsToForget {

  private Charm charm;
  private CompositeLearnWorker worker;

  public CharmsToForget(Charm charm, CharmLearnArbitrator worker) {
    this.charm = charm;
    this.worker = new CompositeLearnWorker(worker);
  }

  public Set<Charm> getLearnFollowUpCharms() {
    charm.forEachChild(child -> addCharmsToForget(child));
    return worker.getForgottenCharms();
  }

  private void addCharmsToForget(Charm charm) {
    if (isCharmPrerequisiteListFulfilled(charm)) {
      return;
    }
    worker.forget(charm);
    charm.forEachChild(child -> addCharmsToForget(child));
  }

  private boolean isCharmPrerequisiteListFulfilled(Charm charm) {
    AllSatisfied allSatisfied = new AllSatisfied(worker, prerequisite -> prerequisite instanceof DirectCharmPrerequisite);
    charm.forEachCharmPrerequisite(allSatisfied);
    return allSatisfied.isFulfilled;
  }
}
