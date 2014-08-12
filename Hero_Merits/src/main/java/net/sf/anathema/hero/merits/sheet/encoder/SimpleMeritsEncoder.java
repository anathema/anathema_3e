package net.sf.anathema.hero.merits.sheet.encoder;

import net.sf.anathema.hero.merits.sheet.content.SimpleMeritsContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.LineFillingContentEncoder;

public class SimpleMeritsEncoder extends LineFillingContentEncoder<SimpleMeritsContent> {

  public SimpleMeritsEncoder() {
    super(SimpleMeritsContent.class);
  }
}
