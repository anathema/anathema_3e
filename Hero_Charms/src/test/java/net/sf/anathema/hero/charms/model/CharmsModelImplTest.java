package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.prerequisite.TraitPrerequisite;
import net.sf.anathema.hero.charms.dummy.DummyCharm;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CharmsModelImplTest {

  @Test
  public void countsEachCharmOnlyOnce() throws Exception {
    CharmsTemplate template = new CharmsTemplate();
    CharmsModelImpl model = new CharmsModelImpl(template);
    TraitPrerequisite essencePrerequisite = new TraitPrerequisite(new RequiredTraitType("Essence"), 1);
    TraitPrerequisite archeryPrerequisite = new TraitPrerequisite(new RequiredTraitType("Archery"), 1);
    DummyCharm charmWithTraitAndEssence = new DummyCharm("id", new Charm[0], archeryPrerequisite, essencePrerequisite);
    model.getLearningModel().learnCharm(charmWithTraitAndEssence, false);
    boolean satisfied = model.hasLearnedThresholdCharmsOfTrait(newArrayList(AbilityType.Archery), null, 2, 1);
    assertThat(satisfied, is(false));
  }
}