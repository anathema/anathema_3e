package net.sf.anathema.hero.charms.model.learn.prerequisites;

import net.sf.anathema.magic.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.charms.model.learn.CharmLearnArbitrator;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class AllSatisfied implements Consumer<CharmPrerequisite> {
  private final CharmLearnArbitrator learnArbitrator;
  private Predicate<CharmPrerequisite> predicate;
  public boolean isFulfilled;

  public AllSatisfied(CharmLearnArbitrator learnArbitrator, Predicate<CharmPrerequisite> predicate) {
    this.learnArbitrator = learnArbitrator;
    this.predicate = predicate;
    isFulfilled = true;
  }

  @Override
  public void accept(CharmPrerequisite prerequisite) {
    if (!isFulfilled || !predicate.test(prerequisite)) {
      return;
    }
    isFulfilled = IsSatisfied.isSatisfied(prerequisite, learnArbitrator);
  }
}
