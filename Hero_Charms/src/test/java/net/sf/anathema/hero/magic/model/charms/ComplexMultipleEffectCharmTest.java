package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.display.special.CharmSpecialistImpl;
import net.sf.anathema.hero.charms.model.learn.CharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.special.subeffects.ComplexMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.IMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffect;
import net.sf.anathema.hero.dummy.DummyHero;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ComplexMultipleEffectCharmTest {
  private CharmSpecialistImpl specialist = new CharmSpecialistImpl(new DummyHero());
  private CharmLearnableArbitrator arbitrator = mock(CharmLearnableArbitrator.class);
  private Charm baseCharm = mock(Charm.class);
  private IMultipleEffectCharm charmWithThreeEffects =
          new ComplexMultipleEffectCharm(new CharmName("Solar.TestCharm"), new String[]{"A", "B", "C"}, new HashMap<>());

  @Test
  public void instantiatesSubeffects() throws Exception {
    List<SubEffect> subeffects = charmWithThreeEffects.buildSubEffects(specialist, arbitrator, baseCharm).getEffects();
    assertThat(subeffects.size(), is(3));
  }

  @Test
  public void instantiatesSubeffectsOnlyOnce() throws Exception {
    charmWithThreeEffects.buildSubEffects(specialist, arbitrator, baseCharm);
    List<SubEffect> subeffectsAgain = charmWithThreeEffects.buildSubEffects(specialist, arbitrator, baseCharm).getEffects();
    assertThat(subeffectsAgain.size(), is(3));
  }
}