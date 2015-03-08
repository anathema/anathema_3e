package net.sf.anathema.hero.charms.model.learn.prerequisites;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.hero.charms.dummy.DummyCharm;
import net.sf.anathema.hero.charms.model.learn.CharmLearnArbitrator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IsSatisfiedTest {
  CharmLearnArbitrator arbitrator = mock(CharmLearnArbitrator.class);
  IsSatisfied isSatisfied = new IsSatisfied(arbitrator);

  @Test
  public void satifiesGroupRequirementsIfEnoughCharmsAreLearned() throws Exception {
    Charm charm1 = createLearnedCharm("charm1");
    Charm charm2 = createLearnedCharm("charm2");
    Charm[] prerequisites = new Charm[]{charm1, charm2};
    isSatisfied.requiresCharmFromSelection(prerequisites, 2);
    assertThat(isSatisfied.satisfied, is(true));
  }

  private Charm createLearnedCharm(String id) {
    Charm charm1 = new DummyCharm(id);
    when(arbitrator.isLearned(charm1)).thenReturn(true);
    return charm1;
  }
}