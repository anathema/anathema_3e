package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

import com.itextpdf.text.DocumentException;

public interface ContentEncoder {

  void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException;

  boolean hasContent(ReportSession session);

  String getHeader(ReportSession session);
}
