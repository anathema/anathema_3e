package net.sf.anathema.hero.sheet.pdf.page;

import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncoderRegistry;
import net.sf.anathema.library.resources.Resources;

import java.util.Collections;
import java.util.List;

public class NullPageFactory implements PageFactory {

  @Override
  public List<PageEncoder> create(EncoderRegistry encoderRegistry, Resources resources, PageSize pageSize) {
    return Collections.emptyList();
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}
