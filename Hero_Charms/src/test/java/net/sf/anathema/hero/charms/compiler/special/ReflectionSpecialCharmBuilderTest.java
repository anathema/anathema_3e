package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.magic.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.library.dummy.ConfigurableDummyObjectFactory;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public class ReflectionSpecialCharmBuilderTest {

  private ConfigurableDummyObjectFactory factory = new ConfigurableDummyObjectFactory();

  @Test
  public void returnsCharmFromRegisteredBuilder() {
    SpecialCharmTemplate dto = new SpecialCharmTemplate();
    CharmSpecialLearning charm = mock(CharmSpecialLearning.class);
    registerBuilderForDtoYieldingCharm(dto, charm);
    CharmSpecialLearning specialCharm = new ReflectionSpecialCharmBuilder(factory).readCharmLearning(dto, null, null);
    assertThat(specialCharm, is(charm));
  }

  private void registerBuilderForDtoYieldingCharm(SpecialCharmTemplate dto, CharmSpecialLearning charm) {
    ConfigurableDummySpecialCharmBuilder builder = new ConfigurableDummySpecialCharmBuilder();
    builder.support(dto).with(charm);
    factory.add(CharmSpecialLearningBuilder.class, builder);
  }
}