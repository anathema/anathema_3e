package net.sf.anathema.character.lunar.reporting.rendering.beastform;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.BoxEncodingUtils;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxEncoder;

public class BeastformBoxEncoder implements IBoxEncoder {
  private final BaseFont baseFont;
  private final float smallWidth;
  private float smallHeight;

  public BeastformBoxEncoder(BaseFont baseFont, float smallWidth, float smallHeight) {
    this.baseFont = baseFont;
    this.smallWidth = smallWidth;
    this.smallHeight = smallHeight - (HEADER_HEIGHT / 2);
  }

  public void encodeContentBox(SheetGraphics graphics, Bounds contentBounds) {
    PdfContentByte directContent = graphics.getDirectContent();
    float smallMaxX = contentBounds.x + smallWidth;
    graphics.setFillColorBlack();
    directContent.setLineWidth(0.5f);
    directContent.moveTo(contentBounds.x, contentBounds.y + ARCSPACE);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x, contentBounds.y, 180);
    directContent.moveTo(contentBounds.x + ARCSPACE, contentBounds.y);
    directContent.lineTo(smallMaxX - ARCSPACE, contentBounds.y);
    BoxEncodingUtils.add90DegreeArc(directContent, smallMaxX - ARC_SIZE, contentBounds.y, 270);
    directContent.moveTo(smallMaxX, contentBounds.y + ARCSPACE);
    directContent.lineTo(smallMaxX, contentBounds.getMaxY() - ARCSPACE - smallHeight);
    BoxEncodingUtils.add90DegreeArc(directContent, smallMaxX, contentBounds.getMaxY() - smallHeight - ARC_SIZE, 90);
    directContent.moveTo(smallMaxX + ARCSPACE, contentBounds.getMaxY() - smallHeight);
    directContent.lineTo(contentBounds.getMaxX() - ARCSPACE, contentBounds.getMaxY() - smallHeight);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.getMaxX() - ARC_SIZE, contentBounds.getMaxY() - smallHeight, 270);
    directContent.moveTo(contentBounds.getMaxX(), contentBounds.getMaxY() + ARCSPACE - smallHeight);
    directContent.lineTo(contentBounds.getMaxX(), contentBounds.getMaxY() - ARCSPACE);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.getMaxX() - ARC_SIZE, contentBounds.getMaxY() - ARC_SIZE, 0);
    directContent.moveTo(contentBounds.getMinX() + ARCSPACE, contentBounds.getMaxY());
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x, contentBounds.getMaxY() - ARC_SIZE, 90);
    directContent.moveTo(contentBounds.x, contentBounds.getMaxY() - ARCSPACE);
    directContent.lineTo(contentBounds.x, contentBounds.y + ARCSPACE);
    directContent.stroke();
  }
}
