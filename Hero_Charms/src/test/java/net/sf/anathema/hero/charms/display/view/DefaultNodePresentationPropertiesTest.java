package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.lib.dummy.DummyResources;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultNodePresentationPropertiesTest {

  @Test
  public void translatesMultipleCharmsOfACertainTraitWithEssenceRequirement() throws Exception {
    String id = "Requirement.Socialize.Essence3.4";
    FunctionalNodeProperties requirementProperties = new DefaultFunctionalNodeProperties();
    DummyResources resources = new DummyResources();
    resources.putString("Requirement.Message", "{0,number,integer} {1}{2} {3}");
    resources.putString("Requirement.Socialize", "Socialize");
    resources.putString("Essence", "Essence");
    resources.putString("Charms.Charm.Multiple", "Charms");
    CharmMap map = null;
    DefaultNodePresentationProperties properties = new DefaultNodePresentationProperties(resources, requirementProperties, map);
    String nodeText = properties.getNodeText(id);
    assertThat(nodeText, is("4 Essence 3+ Socialize Charms"));
  }
}