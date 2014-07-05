package net.sf.anathema.hero.application;

import net.sf.anathema.library.initialization.ObjectFactory;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class InjectingObjectFactory2Test {
  @Test
  public void injectsClassWithMatchingAnnotatedField() throws Exception {
    ObjectFactory original = Mockito.mock(ObjectFactory.class);
    when(original.instantiateAllImplementers(WithFieldToInject.class)).thenReturn(Collections.singletonList(new WithFieldToInject()));
    InjectingObjectFactory2 injectingObjectFactory2 = new InjectingObjectFactory2(original, "HALLO");
    Collection<WithFieldToInject> instances = injectingObjectFactory2.instantiateAllImplementers(WithFieldToInject.class);
    for (WithFieldToInject instance : instances) {
      assertThat(instance.injectMe, is("HALLO"));
    }
  }

  @Test
  public void doesNotInjectClassWithoutAnnotation() throws Exception {
    ObjectFactory original = Mockito.mock(ObjectFactory.class);
    when(original.instantiateAllImplementers(WithNothingToInject.class)).thenReturn(Collections.singletonList(new WithNothingToInject()));
    InjectingObjectFactory2 injectingObjectFactory2 = new InjectingObjectFactory2(original, "HALLO");
    Collection<WithNothingToInject> instances = injectingObjectFactory2.instantiateAllImplementers(WithNothingToInject.class);
    for (WithNothingToInject instance : instances) {
      assertThat(instance.injectMeNot, is(nullValue()));
    }
  }

  @Test
  public void injectsMatchingType() throws Exception {
    ObjectFactory original = Mockito.mock(ObjectFactory.class);
    when(original.instantiateAllImplementers(WithFieldToInject.class)).thenReturn(Collections.singletonList(new WithFieldToInject()));
    InjectingObjectFactory2 injectingObjectFactory2 = new InjectingObjectFactory2(original, 5);
    Collection<WithFieldToInject> instances = injectingObjectFactory2.instantiateAllImplementers(WithFieldToInject.class);
    for (WithFieldToInject instance : instances) {
      assertThat(instance.injectInteger, is(5));
    }
  }
}