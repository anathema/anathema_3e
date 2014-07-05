package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.hero.dummy.DummyHeroType;
import net.sf.anathema.library.dummy.ConfigurableDummyObjectFactory;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharmDisplayPropertiesMapTest {

  @Test
  public void findsPropertiesForCharacterType() throws Exception {
    TreePresentationProperties expectation = new DummyCharmPresentationProperties();
    ConfigurableDummyObjectFactory factory = new ConfigurableDummyObjectFactory();
    factory.add(CharmPresentationProperties.class, expectation);
    DummyHeroType characterType = new DummyHeroType();
    TreePresentationProperties properties = new CharmDisplayPropertiesMap(factory).getDisplayProperties(characterType);
    assertThat(properties, is(expectation));


  }
}
