package net.sf.anathema.hero.magic.presenter;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeImpl;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.charms.display.presenter.CharmGroupInformer;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.hero.charms.display.coloring.CharmColoring;
import net.sf.anathema.hero.charms.display.coloring.ExternalPrerequisitesColorer;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class ExternalPrerequisitesColorerTest {

  private final CharmColoring coloring = mock(CharmColoring.class);
  private final CharmGroupInformer informer = mock(CharmGroupInformer.class);

  @Test
  public void doesNotColorParentCharmFromCurrentGroup() throws Exception {
    DummyCharm parent = createParentCharmFromGroup("CurrentGroup");
    Charm child = createChildCharm(parent);
    CharmTree currentGroup = createGroupWithCharms(parent, child);
    selectGroup(currentGroup);
    colorAllPrerequisitesOfChild(child);
    verifyZeroInteractions(coloring);
  }

  private void colorAllPrerequisitesOfChild(Charm child) {
    ExternalPrerequisitesColorer colorer = new ExternalPrerequisitesColorer(informer, coloring);
    colorer.color(child);
  }

  private void selectGroup(CharmTree currentGroup) {
    when(informer.getCurrentGroup()).thenReturn(currentGroup);
  }

  private CharmTree createGroupWithCharms(DummyCharm parent, Charm child) {
    CategoryReference categoryReference = new CategoryReference(new DummyMundaneCharacterType().getId());
    TreeReference reference = new TreeReference(categoryReference, new TreeName("CurrentGroup"));
    return new CharmTreeImpl(reference, new Charm[]{parent, child});
  }

  private DummyCharm createParentCharmFromGroup(String group) {
    DummyCharm parent = new DummyCharm("parent");
    parent.setGroupId(group);
    parent.setCharacterType(new DummyMundaneCharacterType());
    return parent;
  }

  private Charm createChildCharm(DummyCharm parent) {
    return new DummyCharm("child", parent);
  }
}
