package net.sf.anathema.hero.combat.sheet.social.stats;

import net.sf.anathema.hero.traits.sheet.content.AbstractValueStatsGroup;
import net.sf.anathema.library.resources.Resources;

public abstract class AbstractSocialCombatsValueStatsGroup extends AbstractValueStatsGroup<ISocialCombatStats> {

  public AbstractSocialCombatsValueStatsGroup(Resources resources, String resourceKey) {
    super(resources, resourceKey);
  }

  @Override
  protected String getHeaderResourceBase() {
    return "Sheet.SocialCombat.";
  }
}
