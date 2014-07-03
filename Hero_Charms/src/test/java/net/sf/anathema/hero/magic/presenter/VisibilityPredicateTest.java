package net.sf.anathema.hero.magic.presenter;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.display.presenter.CharmGroupInformer;
import net.sf.anathema.hero.charms.display.special.VisibilityPredicate;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.magic.charm.Charm;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VisibilityPredicateTest {
  private static final String ANY_ID = "anyId";
  private CharmGroupInformer informer = mock(CharmGroupInformer.class);

  @Test
  public void charmIsVisibleIfCharacterTypesMatchButAreNotIdentical() throws Exception {
    Charm charm = DummyCharm.ForIdAndTree(ANY_ID, ANY_ID);
    CharmMap map = createMapWithCharm(charm);
    CharmTree charmGroup = createACharmGroupThatContainsTheCharm(charm);
    selectGroup(charmGroup);
    VisibilityPredicate predicate = new VisibilityPredicate(map, informer);
    assertThat(predicate.apply(ANY_ID), is(true));
  }

  private CharmTree createACharmGroupThatContainsTheCharm(Charm charm) {
    CharmTree charmGroup = mock(CharmTree.class);
    when(charmGroup.isCharmFromTree(charm)).thenReturn(true);
    return charmGroup;
  }

  private void selectGroup(CharmTree charmGroup) {
    when(informer.hasGroupSelected()).thenReturn(true);
    when(informer.getCurrentTree()).thenReturn(charmGroup);
  }

  private CharmMap createMapWithCharm(Charm charm) {
    CharmMap map = mock(CharmMap.class);
    when(map.getCharmById(new CharmName(ANY_ID))).thenReturn(charm);
    return map;
  }
}