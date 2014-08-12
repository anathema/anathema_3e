package net.sf.anathema.hero.merits.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.library.dependencies.Produces;
import net.sf.anathema.library.resources.Resources;

@Produces(SimpleMeritsContent.class)
public class SimpleIntimaciesContentFactory implements ReportContentFactory<SimpleMeritsContent> {
  private Resources resources;

  public SimpleIntimaciesContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override

  public SimpleMeritsContent create(ReportSession session) {
    return new SimpleMeritsContent(session.getHero(), resources);
  }
}
