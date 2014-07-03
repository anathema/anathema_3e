package net.sf.anathema.hero.magic.charm.prerequisite;

import com.google.common.base.Preconditions;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.UnlinkedCharmMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static net.sf.anathema.hero.magic.charm.prerequisite.CollectPrerequisiteCharms.collectPrerequisiteCharms;

public class DirectGroupCharmPrerequisite implements DirectCharmPrerequisite {

  private final int threshold;
  private final CharmName[] prerequisiteIds;
  private Charm[] prerequisites;


  public DirectGroupCharmPrerequisite(CharmName[] charms, int threshold) {
    this.prerequisiteIds = charms;
    this.threshold = threshold;
  }

  @Override
  public void process(PrerequisiteProcessor processor) {
    processor.requiresCharmFromSelection(prerequisites, threshold);
  }

  @Override
  public void accept(PrerequisiteVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void link(UnlinkedCharmMap charmsById) {
    if (prerequisites != null) {
      return;
    }
    List<Charm> prerequisites = new ArrayList<>();
    for (CharmName id : prerequisiteIds) {
      Charm parentCharm = charmsById.get(id);
      Preconditions.checkNotNull(parentCharm, "Parent Charm " + id + " not defined.");
      prerequisites.add(parentCharm);
    }
    this.prerequisites = prerequisites.toArray(new Charm[prerequisites.size()]);
  }

  @Override
  public Charm[] getDirectPredecessors() {
    return prerequisites;
  }

  public Charm[] getLearnPrerequisites(CharmLearnArbitrator learnArbitrator) {
    Set<Charm> prerequisiteCharms = new LinkedHashSet<>();
    List<Charm> charmsToLearn = selectCharmsToLearn(learnArbitrator);
    for (Charm learnCharm : charmsToLearn) {
      prerequisiteCharms.addAll(collectPrerequisiteCharms(learnCharm, learnArbitrator));
      prerequisiteCharms.add(learnCharm);
    }
    return prerequisiteCharms.toArray(new Charm[prerequisiteCharms.size()]);
  }

  private List<Charm> selectCharmsToLearn(CharmLearnArbitrator learnArbitrator) {
    List<Charm> charmsToLearn = new ArrayList<>();
    for (Charm charm : getDirectPredecessors()) {
      if (charmsToLearn.size() >= threshold) {
        return charmsToLearn;
      }
      if (learnArbitrator.isLearned(charm)) {
        charmsToLearn.add(charm);
      }
    }
    for (Charm charm : getDirectPredecessors()) {
      if (charmsToLearn.size() >= threshold) {
        return charmsToLearn;
      }
      if (!learnArbitrator.isLearned(charm)) {
        charmsToLearn.add(charm);
      }
    }
    return charmsToLearn;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof DirectGroupCharmPrerequisite) {
      DirectGroupCharmPrerequisite prerequisite = (DirectGroupCharmPrerequisite) obj;
      return Arrays.deepEquals(prerequisites, prerequisite.prerequisites) && prerequisite.threshold == threshold;
    }
    return false;
  }
}
