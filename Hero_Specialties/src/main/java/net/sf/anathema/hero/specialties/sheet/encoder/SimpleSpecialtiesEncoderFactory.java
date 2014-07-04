package net.sf.anathema.hero.specialties.sheet.encoder;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;

@SuppressWarnings("UnusedDeclaration")
public class SimpleSpecialtiesEncoderFactory extends GlobalEncoderFactory {

  public SimpleSpecialtiesEncoderFactory() {
    super(EncoderIds.SPECIALTIES_SIMPLE);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new SimpleSpecialtiesEncoder(resources);
  }
}
