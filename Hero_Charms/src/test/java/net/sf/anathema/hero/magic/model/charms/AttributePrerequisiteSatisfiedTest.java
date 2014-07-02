package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.charm.old.attribute.MagicAttributeImpl;
import net.sf.anathema.hero.charms.model.learn.prerequisites.IsSatisfied;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.prerequisite.AttributeKnownCharmPrerequisite;
import net.sf.anathema.lib.util.Identifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class AttributePrerequisiteSatisfiedTest {

  private MagicAttributeImpl attribute;

  @Before
  public void createAttribute() {
    this.attribute = new MagicAttributeImpl("Expected", false);
  }

  @Test
  public void isNotFulfilledWithoutCharms() throws Exception {
    AttributeKnownCharmPrerequisite requirement = new AttributeKnownCharmPrerequisite(attribute, 1);
    Charm[] charms = new Charm[0];
    Assert.assertFalse(isSatisfied(requirement, charms));
  }

  @Test
  public void isFulfilledIfAttributeIsPresent() throws Exception {
    AttributeKnownCharmPrerequisite requirement = new AttributeKnownCharmPrerequisite(attribute, 1);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertTrue(isSatisfied(requirement, new Charm[]{charm}));
  }

  @Test
  public void isNotFulfilledWithoutCorrectAttribute() throws Exception {
    AttributeKnownCharmPrerequisite requirement = new AttributeKnownCharmPrerequisite(attribute, 1);
    DummyCharm charm = new DummyCharm();
    Assert.assertFalse(isSatisfied(requirement, new Charm[]{charm}));
  }

  @Test
  public void isNotFulfilledWithoutCorrectCount() throws Exception {
    AttributeKnownCharmPrerequisite requirement = new AttributeKnownCharmPrerequisite(attribute, 2);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertFalse(isSatisfied(requirement, new Charm[]{charm}));
  }

  @Test
  public void isNotFulfilledWithoutCorrectAttributesAndCount() throws Exception {
    AttributeKnownCharmPrerequisite requirement = new AttributeKnownCharmPrerequisite(attribute, 2);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertFalse(isSatisfied(requirement, new Charm[]{charm, new DummyCharm()}));
  }

  @Test
  public void isFulfilledEvenIfChainIsBroken() throws Exception {
    AttributeKnownCharmPrerequisite requirement = new AttributeKnownCharmPrerequisite(attribute, 2);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertTrue(isSatisfied(requirement, new Charm[]{charm, new DummyCharm(), charm}));
  }

  private boolean isSatisfied(AttributeKnownCharmPrerequisite requirement, Charm[] charms) {
    return IsSatisfied.isSatisfied(requirement, getLearnArbiter(charms));
  }

  private ICharmLearnArbitrator getLearnArbiter(final Charm[] charms) {
	  return new ICharmLearnArbitrator() {

		@Override
		public boolean isLearned(Charm charm) {
			return Arrays.asList(charms).contains(charm);
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
		  
	  };
  }

  private DummyCharm createAttributedDummyCharm() {
    return new DummyCharm() {
      @Override
      public boolean hasAttribute(Identifier charmAttribute) {
        return true;
      }
    };
  }
}