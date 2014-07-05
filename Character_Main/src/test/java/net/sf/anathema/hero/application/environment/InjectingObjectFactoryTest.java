package net.sf.anathema.hero.application.environment;

import net.sf.anathema.library.initialization.ObjectFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InjectingObjectFactoryTest {

  private ObjectFactory mockFactory;

  @Before
  public void setUp() throws Exception {
    mockFactory = mock(ObjectFactory.class);
    TestInjectionInstance instance = new TestInjectionInstance();
    when(mockFactory.instantiateAllImplementers(TestInjectionInstance.class)).thenReturn(singletonList(instance));
  }

  @Test
  public void injectsClassWithMatchingAnnotatedField() throws Exception {
    TestInjectionInstance instance = createdAndInjectedInstance("HALLO");
    assertThat(instance.annotatedString, is("HALLO"));
  }

  @Test
  public void doesNotInjectClassWithoutAnnotation() throws Exception {
    TestInjectionInstance instance = createdAndInjectedInstance("HALLO");
    assertThat(instance.unannotatedString, is(nullValue()));
  }

  @Test
  public void injectsMatchingType() throws Exception {
    TestInjectionInstance instance = createdAndInjectedInstance(5);
    assertThat(instance.annotatedInteger, is(5));
  }

  @Test
  public void injectsIntoSuperType() throws Exception {
    TestInjectionInstance instance = createdAndInjectedInstance(5);
    assertThat(instance.annotatedNumber, is(5));
  }

  private TestInjectionInstance createdAndInjectedInstance(Object... injections) {
    InjectingObjectFactory factory = new InjectingObjectFactory(mockFactory, injections);
    Collection<TestInjectionInstance> createdInstances = factory.instantiateAllImplementers(TestInjectionInstance.class);
    return createdInstances.stream().findFirst().get();
  }

}