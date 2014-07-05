package net.sf.anathema.hero.application;

import net.sf.anathema.hero.application.dummy.ConfigurableViewFactory;
import net.sf.anathema.hero.application.dummy.DummyView;
import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.library.dummy.ConfigurableDummyObjectFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SubViewMapTest {

  private ConfigurableDummyObjectFactory factory = new ConfigurableDummyObjectFactory();

  @Test
  public void containsFactoryForRegisteredViewClass() throws Exception {
    DummyView expectation = new DummyView();
    factory.add(SubViewFactory.class, new ConfigurableViewFactory(expectation));
    DummyView result = new SubViewMap(factory).get(DummyView.class);
    assertThat(result, is(expectation));
  }
}