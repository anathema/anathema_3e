package net.sf.anathema.hero.concept.sheet.text.description;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.concept.model.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.text.HeroTextEncoder;
import net.sf.anathema.hero.sheet.text.MultiColumnTextReport;
import net.sf.anathema.hero.sheet.text.TextPartFactory;
import net.sf.anathema.library.resources.Resources;

public class HeroDescriptionTextEncoder implements HeroTextEncoder {

  private final TextPartFactory partFactory;
  private final Resources resources;

  public HeroDescriptionTextEncoder(PdfReportUtils utils, Resources resources) {
    this.resources = resources;
    this.partFactory = new TextPartFactory(utils);
  }

  public void createParagraphs(MultiColumnTextReport report, Hero hero) throws DocumentException {
    TextDescriptionContentImpl content = new TextDescriptionContentImpl(HeroDescriptionFetcher.fetch(hero));
    report.addElement(partFactory.createTitleParagraph(content.getName()));
    Chunk labelChunk = createDescriptionTitleChunk();
    Phrase descriptionPhrase = partFactory.createTextParagraph(labelChunk);
    boolean isFirst = true;
    for (String descriptionPart : content.getDescription()) {
      Chunk chunk = partFactory.createTextChunk(descriptionPart);
      addTextualDescriptionPart(report, descriptionPhrase, isFirst, chunk);
      isFirst = false;
    }
  }

  private Chunk createDescriptionTitleChunk() {
    String label = resources.getString("TextDescription.Label.Description") + ": ";
    return partFactory.createBoldChunk(label);
  }

  private void addTextualDescriptionPart(MultiColumnTextReport report, Phrase potentialParent, boolean isFirst, Chunk chunk) throws DocumentException {
    if (isFirst) {
      potentialParent.add(chunk);
      report.addElement(potentialParent);
    } else {
      Paragraph descriptionParagraph = partFactory.createTextParagraph(chunk);
      descriptionParagraph.setFirstLineIndent(5f);
      report.addElement(descriptionParagraph);
    }
  }
}
