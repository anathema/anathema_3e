package net.sf.anathema.hero.application.models;

import net.sf.anathema.hero.application.creation.models.ModelFactoryAutoCollector;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.library.dummy.ConfigurableDummyObjectFactory;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ModelFactoryAutoCollectorTest {
  private ConfigurableDummyObjectFactory factory = new ConfigurableDummyObjectFactory();

  @Test
  public void collectsHeroModels() {
    HeroModelFactory expectation = mock(HeroModelFactory.class);
    factory.add(HeroModelFactory.class, expectation);
    Collection<HeroModelFactory> result = new ModelFactoryAutoCollector(factory).collect();
    assertThat(result, CoreMatchers.hasItem(expectation));
  }
}
