package net.sf.anathema.hero.sheet.pdf.content;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.library.dependencies.Produces;
import net.sf.anathema.library.initialization.DoNotInstantiateAutomatically;

@DoNotInstantiateAutomatically
@Produces(DummyContent.class)
public class DummyContentFactory implements ReportContentFactory<DummyContent> {

  @Override
  public DummyContent create(ReportSession session) {
    return new DummyContent();
  }
}