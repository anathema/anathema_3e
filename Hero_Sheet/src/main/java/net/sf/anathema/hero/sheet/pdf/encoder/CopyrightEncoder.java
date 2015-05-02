package net.sf.anathema.hero.sheet.pdf.encoder;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.HorizontalAlignment;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SimpleColumnBuilder;
import net.sf.anathema.hero.sheet.pdf.page.PageConfiguration;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static net.sf.anathema.hero.sheet.pdf.encoder.graphics.HorizontalAlignment.Left;
import static net.sf.anathema.hero.sheet.pdf.encoder.graphics.HorizontalAlignment.Right;
import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.FONT_SIZE;
import static net.sf.anathema.hero.sheet.pdf.page.PageConfiguration.Offset;

public class CopyrightEncoder {

  private final PageConfiguration pageConfiguration;
  private final float contentHeight;


  public CopyrightEncoder(PageConfiguration pageConfiguration, float contentHeight) {
    this.pageConfiguration = pageConfiguration;
    this.contentHeight = contentHeight;
  }

  public void encodeCopyright(SheetGraphics graphics) {
    encodeProgramCopyright(graphics);
    encodeGameCopyright(graphics);
  }

  private void encodeProgramCopyright(SheetGraphics graphics) {
    Bounds bounds = pageConfiguration.getColumnRectangle(contentHeight, getCopyrightHeight(), 1, Offset(0));
    String text = induceYear("Created with Anathema \u00A92007-{0}\nhttp://anathema.github.io");
    Anchor phrase = createAnchor(graphics, text, "http://anathema.github.io");
    encoderLine(graphics, phrase, Left, bounds);
  }

  private void encodeGameCopyright(SheetGraphics graphics) {
    Bounds bounds = pageConfiguration.getColumnRectangle(contentHeight, getCopyrightHeight(), 1, Offset(2));
    String text = induceYear("Exalted \u00A92007-{0} by CCP hf\nhttp://www.theonyxpath.com");
    Anchor phrase = createAnchor(graphics, text, "http://www.theonyxpath.com");
    encoderLine(graphics, phrase, Right, bounds);
  }

  private String induceYear(String pattern) {
    int year = new GregorianCalendar().get(Calendar.YEAR);
    DecimalFormat format = new DecimalFormat("####", new DecimalFormatSymbols(Locale.ENGLISH));
    return MessageFormat.format(pattern, format.format(year));
  }

  private Anchor createAnchor(SheetGraphics graphics, String text, String reference) {
    Anchor phrase = new Anchor(text, getFont(graphics));
    phrase.setReference(reference);
    return phrase;
  }

  private Font getFont(SheetGraphics graphics) {
    return graphics.createCommentFont();
  }

  private float getCopyrightHeight() {
    return pageConfiguration.getPageHeight() - pageConfiguration.getContentHeight();
  }

  private void encoderLine(SheetGraphics graphics, Phrase phrase, HorizontalAlignment alignment, Bounds bounds) {
    SimpleColumnBuilder column = graphics.createSimpleColumn(bounds);
    column.withLeading(FONT_SIZE).andAlignment(alignment).andTextPart(phrase).encode();
  }
}