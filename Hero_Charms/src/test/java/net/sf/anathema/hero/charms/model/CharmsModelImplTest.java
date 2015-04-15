package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.charms.dummy.DummyCharm;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.prerequisite.RequiredTraitType;
import net.sf.anathema.magic.data.prerequisite.TraitPrerequisite;
import net.sf.anathema.magic.data.reference.CategoryReference;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Archery;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CharmsModelImplTest {
  CharmsModelImpl model = new CharmsModelImpl(new CharmsTemplate());

  @Test
  public void countsEachCharmOnlyOnce() throws Exception {
    DummyCharm charmWithTraitAndEssence = createCharmWithArcheryAndEssenceAtOne();
    learnCharm(charmWithTraitAndEssence);
    boolean satisfied = model.hasLearnedThresholdCharmsOfTrait(newArrayList(Archery), null, 2, 1);
    assertThat(satisfied, is(false));
  }

  @Test
  public void countsCharmsWhenNoCategoryIsGiven() throws Exception {
    DummyCharm charmWithTraitAndEssence = createCharmWithArcheryAndEssenceAtOne();
    learnCharm(charmWithTraitAndEssence);
    boolean satisfied = model.hasLearnedThresholdCharmsOfTrait(newArrayList(Archery), null, 1, 1);
    assertThat(satisfied, is(true));
  }

  @Test
  public void countsCharmsWhenCategoriesMatch() throws Exception {
    DummyCharm charmWithTraitAndEssence = createCharmWithArcheryAndEssenceAtOne();
    learnCharm(charmWithTraitAndEssence);
    CategoryReference category = charmWithTraitAndEssence.getTreeReference().category;
    boolean satisfied = model.hasLearnedThresholdCharmsOfTrait(newArrayList(Archery), category, 1, 1);
    assertThat(satisfied, is(true));
  }

  private void learnCharm(DummyCharm charmWithTraitAndEssence) {
    model.getLearningModel().learnCharm(charmWithTraitAndEssence, false);
  }

  private DummyCharm createCharmWithArcheryAndEssenceAtOne() {
    TraitPrerequisite essencePrerequisite = new TraitPrerequisite(new RequiredTraitType("Essence"), 1);
    TraitPrerequisite archeryPrerequisite = new TraitPrerequisite(new RequiredTraitType("Archery"), 1);
    return new DummyCharm("id", new Charm[0], archeryPrerequisite, essencePrerequisite);
  }
}