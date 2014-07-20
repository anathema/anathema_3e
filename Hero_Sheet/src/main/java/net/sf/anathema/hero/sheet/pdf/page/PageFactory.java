package net.sf.anathema.hero.sheet.pdf.page;

import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncoderRegistry;
import net.sf.anathema.library.resources.Resources;

import java.util.List;

public interface PageFactory {

  List<PageEncoder> create(EncoderRegistry encoderRegistry, Resources resources, PageSize pageSize);

  boolean supports(BasicContent content);
}
