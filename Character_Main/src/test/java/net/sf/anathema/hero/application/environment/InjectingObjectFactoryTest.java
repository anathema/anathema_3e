package net.sf.anathema.hero.application.environment;

import net.sf.anathema.library.initialization.ObjectFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singleton;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InjectingObjectFactoryTest {

  private InjectObject injectObject;
  private ObjectFactory objectFactoryMock;
  private InjectingObjectFactory objectUnderTest;

  @Before
  public void setUp() throws Exception {
    this.injectObject = new InjectObject();
    Map<Class, Object> injectObjectsByClass = new HashMap<>();
    injectObjectsByClass.put(Base.class, injectObject);
    objectFactoryMock = mock(ObjectFactory.class);
    this.objectUnderTest = new InjectingObjectFactory(objectFactoryMock, injectObjectsByClass);
  }

  @Test
  public void doesNotInjectUnannotatedField() {
    UnannotatedTarget target = new UnannotatedTarget();
    Object actual = getFirstFromAllInstantiated(target);
    assertThat(actual, is(sameInstance((Object) target)));
  }

  @Test
  public void injectsAnnotatedField() {
    AnnotatedTarget target = new AnnotatedTarget();
    Object actual = getFirstFromAllInstantiated(target);
    assertThat(actual, is(sameInstance((Object) target)));
    assertThat(target.object, is(sameInstance((Base) injectObject)));
  }

  private Object getFirstFromAllInstantiated(Object target) {
    when(objectFactoryMock.instantiateAll(null)).thenReturn(singleton(target));
    Collection<Object> createdObjects = objectUnderTest.instantiateAll(null);
    return createdObjects.stream().findFirst().get();
  }

  public static interface Base {

  }

  public static class InjectObject implements Base  {
    // nothing to do
  }

  public static class AnnotatedTarget {
    @Inject
    public Base object;
  }

  public static class UnannotatedTarget {
    public Base object;
  }
}
