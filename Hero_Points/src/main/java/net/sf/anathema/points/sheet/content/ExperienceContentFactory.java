package net.sf.anathema.points.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.initialization.Produces;

@Produces(ExperienceContent.class)
public class ExperienceContentFactory implements ReportContentFactory<ExperienceContent> {

  private Resources resources;

  public ExperienceContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public ExperienceContent create(ReportSession session) {
    return new ExperienceContent(resources, session.getHero());
  }
}
