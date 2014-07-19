package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import net.sf.anathema.hero.sheet.pdf.content.ListSubBoxContent;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.REDUCED_LINE_HEIGHT;

public class LineFillingContentEncoder<C extends ListSubBoxContent> extends AbstractContentEncoder<C> {

  protected LineFillingContentEncoder(Class<C> contentClass) {
    super(contentClass);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) {
    Phrase phrase = buildPhrase(createContent(reportSession), createDefaultFont(graphics));
    Bounds textBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - 2);
    float yPosition = graphics.createSimpleColumn(textBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode().getYLine();
    yPosition -= REDUCED_LINE_HEIGHT;
    while (yPosition > bounds.y) {
      graphics.createHorizontalLineByCoordinate(new Position(bounds.x, yPosition), bounds.getMaxX()).encode();
      yPosition -= REDUCED_LINE_HEIGHT;
    }
  }

  private Font createDefaultFont(SheetGraphics graphics) {
    return graphics.createTableFont();
  }

  private Phrase buildPhrase(C content, Font font) {
    PhraseBuilder phraseBuilder = new PhraseBuilder();
    phraseBuilder.switchToFont(font);
    if (content.useNewLineForEachEntry()) {
      phraseBuilder.breakAfterEveryItem();
    }
    content.getPrintEntries().forEach(phraseBuilder::addText);
    return phraseBuilder.getPhrase();
  }
}