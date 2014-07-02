package net.sf.anathema.character.main.magic.charm.prerequisite.impl;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.prerequisite.SimpleCharmPrerequisite;
import org.junit.Test;
import org.mockito.Mockito;

public class SimpleCharmPrerequisite_Test {

  @Test(expected = IllegalStateException.class)
  public void cantProvidePrerequisitesUntilLinked() throws Exception {
    SimpleCharmPrerequisite prerequisite = new SimpleCharmPrerequisite(new CharmName("id"));
    ICharmLearnArbitrator arbitrator = Mockito.mock(ICharmLearnArbitrator.class);
    prerequisite.getLearnPrerequisites(arbitrator);
  }
}
