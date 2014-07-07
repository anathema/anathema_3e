package net.sf.anathema.hero.magic.model.charms;

import com.google.common.collect.Lists;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.AttributeKnownCharmPrerequisite;
import net.sf.anathema.hero.charms.dummy.DummyCharm;
import net.sf.anathema.hero.charms.model.learn.CharmLearnArbitrator;
import net.sf.anathema.hero.charms.model.learn.prerequisites.IsSatisfied;
import net.sf.anathema.magic.data.attribute.MagicAttribute;
import net.sf.anathema.magic.data.attribute.MagicAttributeImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.when;

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
		  
	  };
  }

  private Charm createAttributedDummyCharm() {
    Charm charm = Mockito.mock(Charm.class);
    when(charm.hasAttribute(Mockito.any())).thenReturn(true);
    return charm;
  }
}