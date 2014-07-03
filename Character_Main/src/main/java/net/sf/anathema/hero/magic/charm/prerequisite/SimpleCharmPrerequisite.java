package net.sf.anathema.hero.magic.charm.prerequisite;

import com.google.common.base.Preconditions;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.UnlinkedCharmMap;

import java.util.HashSet;
import java.util.Set;

import static net.sf.anathema.hero.magic.charm.prerequisite.CollectPrerequisiteCharms.collectPrerequisiteCharms;

public class SimpleCharmPrerequisite implements DirectCharmPrerequisite {
  private static final Charm PREREQUISITE_NOT_SET = null;
  private final CharmName prerequisiteId;
  private Charm prerequisite;

  public SimpleCharmPrerequisite(CharmName charm) {
    this.prerequisiteId = charm;
  }

  public SimpleCharmPrerequisite(Charm charm) {
    this.prerequisite = charm;
    this.prerequisiteId = charm.getName();
  }

  @Override
  public Charm[] getDirectPredecessors() {
    return new Charm[]{prerequisite};
  }

  @Override
  public Charm[] getLearnPrerequisites(CharmLearnArbitrator arbitrator) {
    if (prerequisite == PREREQUISITE_NOT_SET) {
      throw new IllegalStateException(
              "The prerequisite Charm isn't linked yet. Please call ``link(Map)`` prior to using this object.");
    }
    Set<Charm> prerequisiteCharms = new HashSet<>();
    prerequisiteCharms.addAll(collectPrerequisiteCharms(prerequisite, arbitrator));
    prerequisiteCharms.add(prerequisite);
    return prerequisiteCharms.toArray(new Charm[prerequisiteCharms.size()]);
  }

  @Override
  public void process(PrerequisiteProcessor processor) {
    processor.requiresCharm(prerequisite);
  }

  @Override
  public void accept(PrerequisiteVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void link(UnlinkedCharmMap charmsById) {
    if (prerequisite != PREREQUISITE_NOT_SET) {
      return;
    }
    prerequisite = charmsById.get(prerequisiteId);
    Preconditions.checkNotNull(prerequisite, "Parent Charm " + prerequisiteId + " not defined.");
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof SimpleCharmPrerequisite) {
      SimpleCharmPrerequisite prerequisite = (SimpleCharmPrerequisite) obj;
      return prerequisite.prerequisite.equals(prerequisite);
    }
    return false;
  }
}