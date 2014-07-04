package net.sf.anathema.hero.specialties.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.LineFillingContentEncoder;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.specialties.sheet.content.SimpleSpecialtiesContent;
import net.sf.anathema.library.resources.Resources;

public class SimpleSpecialtiesEncoder extends LineFillingContentEncoder<SimpleSpecialtiesContent> implements ContentEncoder {

  private Resources resources;

  public SimpleSpecialtiesEncoder(Resources resources) {
    super(SimpleSpecialtiesContent.class);
    this.resources = resources;
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.Specialties");
  }
}
