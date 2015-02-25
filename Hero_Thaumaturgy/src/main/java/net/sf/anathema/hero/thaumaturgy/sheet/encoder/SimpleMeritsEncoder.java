package net.sf.anathema.hero.thaumaturgy.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.encoder.boxes.LineFillingContentEncoder;
import net.sf.anathema.hero.thaumaturgy.sheet.content.SimpleMeritsContent;

public class SimpleMeritsEncoder extends LineFillingContentEncoder<SimpleMeritsContent> {

  public SimpleMeritsEncoder() {
    super(SimpleMeritsContent.class);
  }
}
