package net.sf.anathema.hero.charms.model.special.prerequisite;

import com.google.common.collect.Lists;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class PrerequisiteModifyingCharms {
  private final List<IPrerequisiteModifyingCharm> prerequisiteModifyingCharms = Lists.newArrayList();

  public PrerequisiteModifyingCharms(Collection<ISpecialCharm> specialCharms) {
    Stream<ISpecialCharm> charms = specialCharms.stream();
    charms.filter(charm -> charm instanceof IPrerequisiteModifyingCharm).forEach(
            charm -> prerequisiteModifyingCharms.add((IPrerequisiteModifyingCharm) charm));
  }

  public Iterable<IPrerequisiteModifyingCharm> getPrerequisiteModifyingCharms() {
    return prerequisiteModifyingCharms;
  }
}