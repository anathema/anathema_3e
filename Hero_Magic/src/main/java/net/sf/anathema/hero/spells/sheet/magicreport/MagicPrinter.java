package net.sf.anathema.hero.spells.sheet.magicreport;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.text.MultiColumnTextReport;

public interface MagicPrinter {

  void print(MultiColumnTextReport report, Hero hero) throws DocumentException;

  boolean hasData(Hero hero);
}
