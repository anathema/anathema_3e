package net.sf.anathema.hero.sheet.text;

import net.sf.anathema.hero.individual.model.Hero;

import com.itextpdf.text.DocumentException;

public interface HeroTextEncoder {

  void createParagraphs(MultiColumnTextReport report, Hero hero) throws DocumentException;
}
