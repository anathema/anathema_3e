package net.sf.anathema.hero.charms.model.learn.prerequisites;

import net.sf.anathema.hero.magic.charm.prerequisite.PrerequisiteProcessor;
import net.sf.anathema.magic.attribute.MagicAttribute;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmLearnArbitrator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.sf.anathema.hero.magic.charm.prerequisite.ProcessProcessor.process;

public class CollectPrerequisiteCharms implements PrerequisiteProcessor {

  public static Set<Charm> collectPrerequisiteCharms(Charm charm, CharmLearnArbitrator arbitrator) {
    CollectPrerequisiteCharms collectPrerequisiteCharms = new CollectPrerequisiteCharms(arbitrator);
    charm.getPrerequisites().forEachCharmPrerequisite(process(collectPrerequisiteCharms));
    return collectPrerequisiteCharms.prerequisiteCharms;
  }

  public Set<Charm> prerequisiteCharms = new HashSet<>();
  private CharmLearnArbitrator arbitrator;

  public CollectPrerequisiteCharms(CharmLearnArbitrator arbitrator) {
    this.arbitrator = arbitrator;
  }

  @Override
  public void requiresMagicAttributes(MagicAttribute attribute, int count) {
    // nothing to do
  }

  @Override
  public void requiresCharm(Charm prerequisite) {
    prerequisiteCharms.addAll(collectPrerequisiteCharms(prerequisite, arbitrator));
    prerequisiteCharms.add(prerequisite);
  }

  @Override
  public void requiresCharmFromSelection(Charm[] prerequisites, int threshold) {
    List<Charm> charmsToLearn = selectCharmsToLearn(arbitrator, prerequisites, threshold);
    for (Charm learnCharm : charmsToLearn) {
      prerequisiteCharms.addAll(collectPrerequisiteCharms(learnCharm, arbitrator));
      prerequisiteCharms.add(learnCharm);
    }
  }

  private List<Charm> selectCharmsToLearn(CharmLearnArbitrator learnArbitrator, Charm[] prerequisites, int threshold) {
    List<Charm> charmsToLearn = new ArrayList<>();
    for (Charm charm : prerequisites) {
      if (charmsToLearn.size() >= threshold) {
        return charmsToLearn;
      }
      if (learnArbitrator.isLearned(charm)) {
        charmsToLearn.add(charm);
      }
    }
    for (Charm charm : prerequisites) {
      if (charmsToLearn.size() >= threshold) {
        return charmsToLearn;
      }
      if (!learnArbitrator.isLearned(charm)) {
        charmsToLearn.add(charm);
      }
    }
    return charmsToLearn;
  }
}
