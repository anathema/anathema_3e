package net.sf.anathema.hero.spells.sheet.magicreport;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.magic.display.MagicDisplayLabeler;
import net.sf.anathema.hero.magic.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.magic.display.tooltip.source.MagicSourceContributor;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.sheet.text.MultiColumnTextReport;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.magic.description.model.MagicDescription;

public class MagicPrintSupport {

  private final Resources resources;
  private final MagicPartFactory partFactory;
  private final HeroEnvironment environment;

  public MagicPrintSupport(Resources resources, MagicPartFactory partFactory, HeroEnvironment environment) {
    this.resources = resources;
    this.partFactory = partFactory;
    this.environment = environment;
  }

  public void addMagicName(Magic magic, MultiColumnTextReport report) throws DocumentException {
    String charmName = new MagicDisplayLabeler(resources).getLabelForMagic(magic);
    report.addElement(partFactory.createCharmTitle(charmName));
  }

  public void addMagicDescription(Magic magic, MultiColumnTextReport report) throws DocumentException {
    MagicDescription charmDescription = getCharmDescription(magic);
    if (charmDescription.isEmpty()) {
      String sourceString = new MagicSourceContributor<>(resources).createSourceString(magic);
      String sourceReference = resources.getString("MagicReport.See.Source", sourceString);
      report.addElement(partFactory.createDescriptionParagraph(sourceReference));
    }
    for (String paragraph : charmDescription.getParagraphs()) {
      report.addElement(partFactory.createDescriptionParagraph(paragraph));
    }
  }

  private MagicDescription getCharmDescription(Magic magic) {
    return CharmDescriptionProviderExtractor.CreateFor(environment).getCharmDescription(magic);
  }
}