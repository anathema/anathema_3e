package net.sf.anathema.hero.traits.display;

import net.sf.anathema.hero.traits.dummy.DummyTrait;
import net.sf.anathema.hero.traits.model.Trait;

import net.sf.anathema.hero.traits.model.types.CommonTraitTypes;
import org.junit.Test;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Archery;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Athletics;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TraitsTest {
  private final DummyTrait firstTrait = new DummyTrait(Archery);
  private final DummyTrait secondTrait = new DummyTrait(Athletics);

  @Test
  public void startsOffEmpty() throws Exception {
    Traits traits = new Traits();
    assertThat(traits.iterator().hasNext(), is(false));
  }

  @Test
  public void iteratesOverAddedTraits() throws Exception {
    Traits traits = new Traits();
    traits.add(firstTrait);
    traits.add(secondTrait);
    Iterator<Trait> iterator = traits.iterator();
    assertThat(iterator.next(), is(firstTrait));
    assertThat(iterator.next(), is(secondTrait));
  }

  @Test
  public void hasSingleTraitIfCreatedThatWay() throws Exception {
    Traits single = new Traits(secondTrait);
    Iterator<Trait> iterator = single.iterator();
    iterator.next();
    assertThat(iterator.hasNext(), is(false));
  }

  @Test
  public void hasTraitsFromListIfCreatedThatWay() throws Exception {
    List<Trait> traitList = Lists.newArrayList(firstTrait, secondTrait);
    Traits fromList = new Traits(traitList);
    Iterator<Trait> iterator = fromList.iterator();
    assertThat(iterator.next(), is(firstTrait));
    assertThat(iterator.next(), is(secondTrait));
  }
}