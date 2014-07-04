package net.sf.anathema.hero.specialties.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;
import net.sf.anathema.library.resources.Resources;

@SuppressWarnings("UnusedDeclaration")
public class ExtendedSpecialtiesEncoderFactory extends GlobalEncoderFactory {

  public ExtendedSpecialtiesEncoderFactory() {
    super(EncoderIds.SPECIALTIES_EXTENDED);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new ExtendedSpecialtiesEncoder(resources);
  }
}
