package net.sf.anathema.hero.spiritual.sheet.willpower.content;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

@Produces(WillpowerContent.class)
public class WillpowerContentFactory implements ReportContentFactory<WillpowerContent> {

  private final Resources resources;

  public WillpowerContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public WillpowerContent create(ReportSession session) {
    return new WillpowerContent(resources, session.getHero());
  }
}
