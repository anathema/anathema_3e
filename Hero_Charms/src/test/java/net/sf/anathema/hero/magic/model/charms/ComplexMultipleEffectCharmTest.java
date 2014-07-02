package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.hero.charms.display.special.CharmSpecialistImpl;
import net.sf.anathema.hero.charms.model.special.subeffects.ComplexMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.IMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffect;
import net.sf.anathema.hero.dummy.DummyHero;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ComplexMultipleEffectCharmTest {
  private CharmSpecialistImpl specialist = new CharmSpecialistImpl(new DummyHero());
  private ICharmLearnableArbitrator arbitrator = mock(ICharmLearnableArbitrator.class);
  private Charm baseCharm = mock(Charm.class);
  private IMultipleEffectCharm charmWithThreeEffects =
          new ComplexMultipleEffectCharm(new CharmName("Solar.TestCharm"), new String[]{"A", "B", "C"}, new HashMap<>());

  @Test
  public void instantiatesSubeffects() throws Exception {
    SubEffect[] subeffects = charmWithThreeEffects.buildSubEffects(specialist, arbitrator, baseCharm).getEffects();
    assertThat(subeffects.length, is(3));
  }

  @Test
  public void instantiatesSubeffectsOnlyOnce() throws Exception {
    charmWithThreeEffects.buildSubEffects(specialist, arbitrator, baseCharm);
    SubEffect[] subeffectsAgain = charmWithThreeEffects.buildSubEffects(specialist, arbitrator, baseCharm).getEffects();
    assertThat(subeffectsAgain.length, is(3));
  }
}