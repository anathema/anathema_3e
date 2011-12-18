package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Line;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;

public class PdfLineEncodingUtilities {

  public static void encodeHorizontalLines(PdfContentByte directContent, Position lineStartPosition, float minX, float maxX, float lineHeight,
    int lineCount) {
    if (lineCount <= 0) {
      return;
    }
    Line.createHorizontalByCoordinate(lineStartPosition, maxX).encode(directContent);
    for (int index = 1; index < lineCount; index++) {
      lineStartPosition = new Position(minX, lineStartPosition.y - lineHeight);
      Line.createHorizontalByCoordinate(lineStartPosition, maxX).encode(directContent);
    }
  }
}
