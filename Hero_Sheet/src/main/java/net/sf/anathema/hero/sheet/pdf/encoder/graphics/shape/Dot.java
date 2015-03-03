package net.sf.anathema.hero.sheet.pdf.encoder.graphics.shape;

import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;

import com.itextpdf.text.pdf.PdfContentByte;

public class Dot extends AbstractShape {

  private int dotSize;

  public Dot(PdfContentByte directContent, int dotSize) {
    super(directContent);
    this.dotSize = dotSize;
  }

  @Override
  protected void configureShape(Position lowerLeft) {
    float upperRightX = lowerLeft.x + dotSize;
    float upperRightY = lowerLeft.y + dotSize;
    getDirectContent().arc(lowerLeft.x, lowerLeft.y, upperRightX, upperRightY, 0, 360);
  }
}
