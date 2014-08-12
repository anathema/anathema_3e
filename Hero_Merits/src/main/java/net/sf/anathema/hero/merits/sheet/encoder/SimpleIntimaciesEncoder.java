package net.sf.anathema.hero.merits.sheet.encoder;

import net.sf.anathema.hero.merits.sheet.content.SimpleMeritsContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.LineFillingContentEncoder;

public class SimpleIntimaciesEncoder extends LineFillingContentEncoder<SimpleMeritsContent> {

  public SimpleIntimaciesEncoder() {
    super(SimpleMeritsContent.class);
  }
}
