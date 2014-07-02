package net.sf.anathema.character.main.magic.charm.prerequisite.impl;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.prerequisite.SimpleCharmLearnPrerequisite;
import org.junit.Test;
import org.mockito.Mockito;

public class SimpleCharmLearnPrerequisite_Test {

  @Test(expected = IllegalStateException.class)
  public void cantProvidePrerequisitesUntilLinked() throws Exception {
    SimpleCharmLearnPrerequisite prerequisite = new SimpleCharmLearnPrerequisite(new CharmName("id"));
    ICharmLearnArbitrator arbitrator = Mockito.mock(ICharmLearnArbitrator.class);
    prerequisite.getLearnPrerequisites(arbitrator);
  }
}
