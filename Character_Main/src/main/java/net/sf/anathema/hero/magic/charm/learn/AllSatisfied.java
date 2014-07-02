package net.sf.anathema.hero.magic.charm.learn;

import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmLearnPrerequisite;

import java.util.function.Consumer;
import java.util.function.Predicate;

class AllSatisfied implements Consumer<CharmLearnPrerequisite> {
  private final ICharmLearnArbitrator learnArbitrator;
  private Predicate<CharmLearnPrerequisite> predicate;
  public boolean isFulfilled;

  public AllSatisfied(ICharmLearnArbitrator learnArbitrator, Predicate<CharmLearnPrerequisite> predicate) {
    this.learnArbitrator = learnArbitrator;
    this.predicate = predicate;
    isFulfilled = true;
  }

  @Override
  public void accept(CharmLearnPrerequisite prerequisite) {
    if (!isFulfilled || !predicate.test(prerequisite)) {
      return;
    }
    IsSatisfied satisfied = new IsSatisfied(learnArbitrator);
    prerequisite.accept(satisfied);
    isFulfilled = satisfied.satisfied;
  }
}
