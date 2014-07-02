package net.sf.anathema.hero.sheet.text;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.model.Hero;

public interface HeroTextEncoder {

  void createParagraphs(MultiColumnTextReport report, Hero hero) throws DocumentException;
}
