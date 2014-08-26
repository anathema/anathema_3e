package net.sf.anathema.hero.magic.model.charms;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.AttributeKnownCharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.dummy.DummyCharm;
import net.sf.anathema.hero.charms.model.learn.CharmLearnArbitrator;
import net.sf.anathema.hero.charms.model.learn.prerequisites.IsSatisfied;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.magic.data.attribute.MagicAttribute;
import net.sf.anathema.magic.data.attribute.MagicAttributeImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.Lists;

public class AttributePrerequisiteSatisfiedTest {

  private MagicAttributeImpl attribute;

  @Before
  public void createAttribute() {
    this.attribute = new MagicAttributeImpl("Expected", false);
  }

  @Test
  public void isNotFulfilledWithoutCharms() throws Exception {
    AttributeKnownCharmPrerequisite requirement = new AttributeKnownCharmPrerequisite(attribute, 1);
    Assert.assertFalse(isSatisfied(requirement, Collections.emptyList()));
  }

  @Test
  public void isFulfilledIfAttributeIsPresent() throws Exception {
    AttributeKnownCharmPrerequisite requirement = new AttributeKnownCharmPrerequisite(attribute, 1);
    Charm charm = createAttributedDummyCharm();
    Assert.assertTrue(isSatisfied(requirement, Lists.newArrayList(charm)));
  }

  @Test
  public void isNotFulfilledWithoutCorrectAttribute() throws Exception {
    AttributeKnownCharmPrerequisite requirement = new AttributeKnownCharmPrerequisite(attribute, 1);
    DummyCharm charm = new DummyCharm();
    Assert.assertFalse(isSatisfied(requirement, Lists.newArrayList(charm)));
  }

  @Test
  public void isNotFulfilledWithoutCorrectCount() throws Exception {
    AttributeKnownCharmPrerequisite requirement = new AttributeKnownCharmPrerequisite(attribute, 2);
    Charm charm = createAttributedDummyCharm();
    Assert.assertFalse(isSatisfied(requirement, Lists.newArrayList(charm)));
  }

  @Test
  public void isNotFulfilledWithoutCorrectAttributesAndCount() throws Exception {
    AttributeKnownCharmPrerequisite requirement = new AttributeKnownCharmPrerequisite(attribute, 2);
    Charm charm = createAttributedDummyCharm();
    Assert.assertFalse(isSatisfied(requirement, Lists.newArrayList(charm, new DummyCharm())));
  }

  @Test
  public void isFulfilledEvenIfChainIsBroken() throws Exception {
    AttributeKnownCharmPrerequisite requirement = new AttributeKnownCharmPrerequisite(attribute, 2);
    Charm charm = createAttributedDummyCharm();
    Assert.assertTrue(isSatisfied(requirement, Lists.newArrayList(charm, new DummyCharm(), charm)));
  }

  private boolean isSatisfied(AttributeKnownCharmPrerequisite requirement, Collection<Charm> charms) {
    return IsSatisfied.isSatisfied(requirement, getLearnArbiter(charms));
  }

  private CharmLearnArbitrator getLearnArbiter(Collection<Charm> charms) {
	  return new CharmLearnArbitrator() {

			@Override
			public boolean isLearned(Charm charm) {
				return charms.contains(charm);
			}
				
			@Override
			public boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute, int threshold) {
				int count = 0;
				for (Charm charm : charms) {
					if (charm.hasAttribute(attribute)) {
						count++;
					}
					if (count >= threshold) {
						return true;
					}
				}
				return false;
			}
			
			@Override
			public boolean hasLearnedThresholdCharmsWithKeywordFromTree(
					TreeReference tree, MagicAttribute attribute, int threshold) {
				int count = 0;
				List<Charm> validCharms = new ArrayList<>(charms);
				validCharms.removeIf(charm -> !charm.getTreeReference().equals(tree));
				for (Charm charm : validCharms) {
					if (charm.hasAttribute(attribute)) {
						count++;
					}
					if (count >= threshold) {
						return true;
					}
				}
				return false;
			}
	
			@Override
			public boolean hasLearnedThresholdCharmsOfTrait(List<TraitType> traits,
					CategoryReference category, int threshold, int minimumEssence) {
				// TODO: Way to represent current Essence in the test
				return false;
			}

			@Override
			public boolean hasLearnedThresholdCharmsOfAnyOneTrait(int threshold) {
				Map<RequiredTraitType, Integer> groupCounts = new HashMap<>();
				
				for (Charm charm : charms) {
					RequiredTraitType group = charm.getPrerequisites().getPrimaryTraitType();
					Integer currentCount = groupCounts.get(group);
					if (currentCount == null) {
						currentCount = 0;
						groupCounts.put(group, currentCount);
					}
					if (++currentCount >= threshold) {
						return true;
					}
				}
				
				return false;
			}
	  };
  }

  private Charm createAttributedDummyCharm() {
    Charm charm = Mockito.mock(Charm.class);
    when(charm.hasAttribute(Mockito.any())).thenReturn(true);
    return charm;
  }
}