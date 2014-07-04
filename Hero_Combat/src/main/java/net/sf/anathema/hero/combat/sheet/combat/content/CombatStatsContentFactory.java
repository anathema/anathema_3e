package net.sf.anathema.hero.combat.sheet.combat.content;

import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.initialization.Produces;

@Produces(CombatStatsContent.class)
public class CombatStatsContentFactory implements ReportContentFactory<CombatStatsContent> {
  private Resources resources;

  public CombatStatsContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public CombatStatsContent create(ReportSession session) {
    return new CombatStatsContent(session.getHero(), resources);
  }
}
