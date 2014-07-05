package net.sf.anathema.hero.concept.sheet.text.concept;

import com.google.common.base.Strings;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.elsewhere.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.hero.sheet.text.HeroTextEncoder;
import net.sf.anathema.hero.sheet.text.MultiColumnTextReport;
import net.sf.anathema.hero.sheet.text.TextPartFactory;
import net.sf.anathema.library.resources.Resources;

public class ConceptTextEncoder implements HeroTextEncoder {

  private final TextPartFactory partFactory;
  private Resources resources;

  public ConceptTextEncoder(PdfReportUtils utils, Resources resources) {
    this.resources = resources;
    this.partFactory = new TextPartFactory(utils);
  }

  public void createParagraphs(MultiColumnTextReport report, Hero hero) throws DocumentException {
    createCasteParagraph(report, hero);
    createConceptParagraph(report, hero);
  }

  private void createCasteParagraph(MultiColumnTextReport report, Hero hero) throws DocumentException {
    CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
    CharacterType characterType = hero.getSplat().getTemplateType().getCharacterType();
    String labelKey = "Sheet.Label.Caste." + characterType.getId();
    addLabeledText(report, labelKey, casteType.getId());
  }

  private void createConceptParagraph(MultiColumnTextReport report, Hero hero) throws DocumentException {
    String conceptText = HeroDescriptionFetcher.fetch(hero).getConcept().getText();
    if (!Strings.isNullOrEmpty(conceptText)) {
      String labelKey = "Sheet.Label.Concept";
      addLabeledText(report, labelKey, conceptText);
    }
  }

  private void addLabeledText(MultiColumnTextReport report, String labelKey, String text) throws DocumentException {
    Phrase phrase = createLabelPhrase(labelKey);
    phrase.add(partFactory.createTextChunk(text));
    report.addElement(phrase);
  }

  private Phrase createLabelPhrase(String key) {
    String label = resources.getString(key) + ": ";
    Chunk labelChunk = partFactory.createBoldChunk(label);
    return partFactory.createTextParagraph(labelChunk);
  }
}
