package net.sf.anathema.hero.specialties.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.library.autocollect.Produces;
import net.sf.anathema.library.resources.Resources;

@Produces(SimpleSpecialtiesContent.class)
public class SimpleSpecialtiesContentFactory implements ReportContentFactory<SimpleSpecialtiesContent> {
  private Resources resources;

  public SimpleSpecialtiesContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public SimpleSpecialtiesContent create(ReportSession session) {
    return new SimpleSpecialtiesContent(session.getHero(), resources);
  }
}
