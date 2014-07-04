package net.sf.anathema.hero.traits.sheet.encoder;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.text.HeroTextEncoder;
import net.sf.anathema.hero.sheet.text.MultiColumnTextReport;
import net.sf.anathema.hero.sheet.text.TextPartFactory;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;

public abstract class AbstractTraitTextEncoder extends TextPartFactory implements HeroTextEncoder {

  private Resources resources;

  public AbstractTraitTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils);
    this.resources = resources;
  }

  @Override
  public void createParagraphs(MultiColumnTextReport report, Hero hero) throws DocumentException {
    Phrase traitPhrase = createTextParagraph(createBoldChunk(resources.getString(getLabelKey()) + ": "));
    boolean firstPrinted = true;
    for (TraitType type : getTypes(hero)) {
      Trait trait = TraitModelFetcher.fetch(hero).getTrait(type);
      if (trait.getCurrentValue() == 0) {
        continue;
      }
      if (!firstPrinted) {
        traitPhrase.add(createTextChunk(", "));
      }
      firstPrinted = false;
      if (trait.isCasteOrFavored()) {
        traitPhrase.add(createTextChunk("*"));
      }
      traitPhrase.add(createTextChunk(resources.getString(trait.getType().getId())));
      traitPhrase.add(createTextChunk(" " + String.valueOf(trait.getCurrentValue())));
    }
    report.addElement(traitPhrase);
  }

  protected abstract TraitType[] getTypes(Hero hero);

  protected abstract String getLabelKey();
}
