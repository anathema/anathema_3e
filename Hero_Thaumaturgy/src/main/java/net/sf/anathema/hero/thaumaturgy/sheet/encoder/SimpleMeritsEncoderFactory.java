package net.sf.anathema.hero.thaumaturgy.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.library.resources.Resources;

@SuppressWarnings("UnusedDeclaration")
public class SimpleMeritsEncoderFactory extends AbstractEncoderFactory {

  public SimpleMeritsEncoderFactory() {
    super(EncoderIds.INTIMACIES_SIMPLE);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new SimpleMeritsEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}
