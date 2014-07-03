package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.old.cost.CostListImpl;
import net.sf.anathema.charm.old.source.SourceBook;
import net.sf.anathema.hero.charms.compiler.UnlinkedCharms;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.charm.duration.SimpleDuration;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.magic.parser.charms.CharmPrerequisiteList;
import net.sf.anathema.hero.traits.model.types.ValuedTraitType;
import org.junit.Test;

import static net.sf.anathema.hero.magic.charm.duration.SimpleDuration.getDuration;
import static net.sf.anathema.hero.traits.model.types.AbilityType.Archery;
import static net.sf.anathema.hero.traits.model.types.OtherTraitType.Essence;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

public class CharmTest {

  private CharmPrerequisiteList anyPrerequisites = new CharmPrerequisiteList(
          new ValuedTraitType[]{new ValuedTraitType(Archery, 5)}, new ValuedTraitType(Essence, 3),
          new CharmPrerequisite[0]);
  private TreeReference anyTree = new TreeReference(new CategoryReference("anyCategory"), new TreeName("anyTree"));
  private CharmName anyName = new CharmName("anyName");
  private CostListImpl anyCost = new CostListImpl(null, null, null, null);
  private SimpleDuration anyDuration = getDuration("Duration");
  private CharmType anyType = CharmType.Simple;
  private SourceBook[] anySources = new SourceBook[0];

  @Test
  public void doesNotOverwriteExistingParentCharms() throws Exception {
    DummyCharm dummy = new DummyCharm("OtherDummy");
    CharmImpl out = new CharmImpl(anyTree, anyName, anyPrerequisites, anyCost, anyDuration, anyType, anySources);
    out.addParentCharms(dummy);
    CharmImpl charm = out;
    UnlinkedCharms unlinkedCharms = mock(UnlinkedCharms.class);
    charm.extractParentCharms(unlinkedCharms);
    verifyZeroInteractions(unlinkedCharms);
  }

  @Test(expected = NullPointerException.class)
  public void doesNotCreateCharmWithoutSources() throws Exception {
    new CharmImpl(anyTree, anyName, anyPrerequisites, anyCost, anyDuration, anyType, null);
  }
}