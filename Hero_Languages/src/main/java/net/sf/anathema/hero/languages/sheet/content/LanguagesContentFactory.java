package net.sf.anathema.hero.languages.sheet.content;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

@Produces(LanguagesContent.class)
public class LanguagesContentFactory implements ReportContentFactory<LanguagesContent> {

  private Resources resources;

  public LanguagesContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public LanguagesContent create(ReportSession session) {
    return new LanguagesContent(session.getHero(), resources);
  }
}
