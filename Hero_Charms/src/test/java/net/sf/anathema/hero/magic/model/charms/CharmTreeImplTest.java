package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.hero.charms.model.CharmTreeImpl;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CharmTreeImplTest {

  private static final String Default_Group_Id = "AnyId";

  @Test
  public void equalsSelf() throws Exception {
    CharmTreeImpl group = createGroupWithCharacterType();
    assertThat(group.equals(group), is(true));
  }

  @Test
  public void doesNotEqualSimilarGroup() throws Exception {
    CharmTreeImpl group = createGroupWithCharacterType();
    CharmTreeImpl group2 = createGroupWithCharacterType();
    assertThat(group.equals(group2), is(false));
  }

  @Test
  public void identifiesContainedCharm() throws Exception {
    CharmTreeImpl group = createGroupWithCharacterType(new DummyExaltCharacterType());
    Charm charm = CharmMother.createCharmForCharacterTypeFromGroup(new DummyExaltCharacterType(), Default_Group_Id);
    assertThat(group.isCharmFromTree(charm), is(true));

  }

  private CharmTreeImpl createGroupWithCharacterType() {
    return createGroupWithCharacterType(new DummyExaltCharacterType());
  }

  private CharmTreeImpl createGroupWithCharacterType(DummyExaltCharacterType type) {
    return new CharmTreeImpl(type, Default_Group_Id, new Charm[0]);
  }
}