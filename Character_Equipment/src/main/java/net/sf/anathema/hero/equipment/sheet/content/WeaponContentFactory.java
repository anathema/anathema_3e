package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.initialization.Produces;

@Produces(WeaponContent.class)
public class WeaponContentFactory implements ReportContentFactory<WeaponContent> {

  private Resources resources;

  public WeaponContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public WeaponContent create(ReportSession session) {
    return new WeaponContent(session.getHero(), resources);
  }
}
