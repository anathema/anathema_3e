package net.sf.anathema.hero.magic.presenter;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.display.coloring.CharmColoring;
import net.sf.anathema.hero.charms.display.coloring.ExternalPrerequisitesBrush;
import net.sf.anathema.hero.charms.display.presenter.CharmGroupInformer;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeImpl;
import net.sf.anathema.hero.dummy.DummyCharm;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class ExternalPrerequisitesColorerTest {

  public static final String CURRENT_GROUP = "CurrentGroup";
  private final CharmColoring coloring = mock(CharmColoring.class);
  private final CharmGroupInformer informer = mock(CharmGroupInformer.class);

  @Test
  public void doesNotColorParentCharmFromCurrentGroup() throws Exception {
    DummyCharm parent = createParentCharmFromGroup(CURRENT_GROUP);
    Charm child = createChildCharm(parent);
    CharmTree currentGroup = createGroupWithCharms(parent, child);
    selectGroup(currentGroup);
    colorAllPrerequisitesOfChild(child);
    verifyZeroInteractions(coloring);
  }

  private void colorAllPrerequisitesOfChild(Charm child) {
    ExternalPrerequisitesBrush colorer = new ExternalPrerequisitesBrush(informer, coloring);
    colorer.color(child);
  }

  private void selectGroup(CharmTree currentGroup) {
    when(informer.getCurrentTree()).thenReturn(currentGroup);
  }

  private CharmTree createGroupWithCharms(DummyCharm parent, Charm child) {
    TreeReference treeReference = child.getTreeReference();
    return new CharmTreeImpl(treeReference, new Charm[] { parent, child });
  }

  private DummyCharm createParentCharmFromGroup(String treeName) {
    return DummyCharm.ForIdAndTree("parent", treeName);
  }

  private Charm createChildCharm(DummyCharm parent) {
    DummyCharm child = new DummyCharm("child", parent);
    child.treeReference = parent.treeReference;
    return child;
  }
}
