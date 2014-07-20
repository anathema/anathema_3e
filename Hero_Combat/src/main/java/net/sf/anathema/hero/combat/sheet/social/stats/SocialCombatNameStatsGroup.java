package net.sf.anathema.hero.combat.sheet.social.stats;

import net.sf.anathema.hero.sheet.pdf.content.stats.AbstractNameStatsGroup;
import net.sf.anathema.hero.sheet.pdf.encoder.table.TableColumns;
import net.sf.anathema.library.resources.Resources;

public class SocialCombatNameStatsGroup extends AbstractNameStatsGroup<ISocialCombatStats> {

  public SocialCombatNameStatsGroup(Resources resources) {
    super(resources);
  }

  @Override
  public TableColumns getColumnWeights() {
    return TableColumns.singleColumn(3);
  }

  @Override
  protected String getHeaderResourceKey() {
    return getResourceBase() + "AttackName";
  }

  @Override
  protected String getResourceBase() {
    return "Sheet.SocialCombat.";
  }
}
