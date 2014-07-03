package net.sf.anathema.hero.magic.model.rules;

import net.sf.anathema.hero.charms.model.rules.CharmsRulesImpl;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.dummy.DummyCasteType;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;
import org.junit.Test;

import static net.sf.anathema.hero.magic.testing.CharmObjectMother.createMartialArtsCharm;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CharmRulesTest {

  private CharmsTemplate template = new CharmsTemplate();
  private Charm celestialMartialArmsCharm = createMartialArtsCharm(MartialArtsLevel.Celestial);
  private Charm siderealMartialArmsCharm = createMartialArtsCharm(MartialArtsLevel.Sidereal);

  @Test
  public void allowsAlienCharmsForConfiguredCastes() throws Exception {
    template.alienCharmCastes.add("AlienAllowed");
    CharmsRulesImpl charmsRules = new CharmsRulesImpl(template);
    assertThat(charmsRules.isAllowedAlienCharms(new DummyCasteType("AlienAllowed")), is(true));
  }

  @Test
  public void forbidsAlienCharmsForNotConfiguredCastes() throws Exception {
    CharmsRulesImpl charmsRules = new CharmsRulesImpl(template);
    assertThat(charmsRules.isAllowedAlienCharms(new DummyCasteType("AlienForbidden")), is(false));
  }
}
