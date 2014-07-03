package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.dummy.DummyCharm;
import net.sf.anathema.hero.charms.model.CharmTreeImpl;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CharmTreeImplTest {

  private static final String ANY_ID = "AnyId";

  @Test
  public void equalsSelf() throws Exception {
    TreeReference reference = new TreeReference(new CategoryReference(ANY_ID), new TreeName(ANY_ID));
    CharmTreeImpl group = createGroupWithCharacterType(reference);
    assertThat(group.equals(group), is(true));
  }

  @Test
  public void doesNotEqualSimilarGroup() throws Exception {
    TreeReference reference = new TreeReference(new CategoryReference(ANY_ID), new TreeName(ANY_ID));
    CharmTreeImpl group = createGroupWithCharacterType(reference);
    CharmTreeImpl group2 = createGroupWithCharacterType(reference);
    assertThat(group.equals(group2), is(false));
  }

  @Test
  public void identifiesContainedCharm() throws Exception {
    Charm charm =  new DummyCharm(ANY_ID);
    CharmTreeImpl tree = createGroupWithCharacterType(charm.getTreeReference());
    assertThat(tree.isCharmFromTree(charm), is(true));

  }

  private CharmTreeImpl createGroupWithCharacterType(TreeReference reference) {
    return new CharmTreeImpl(reference, new Charm[0]);
  }
}