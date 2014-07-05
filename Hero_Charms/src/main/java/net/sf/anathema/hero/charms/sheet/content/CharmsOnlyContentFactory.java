package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.library.dependencies.Produces;
import net.sf.anathema.library.resources.Resources;

@Produces(CharmsOnlyContent.class)
public class CharmsOnlyContentFactory implements ReportContentFactory<CharmsOnlyContent> {

  private Resources resources;

  public CharmsOnlyContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public CharmsOnlyContent create(ReportSession session) {
    return new CharmsOnlyContent (session, resources);
  }
}
