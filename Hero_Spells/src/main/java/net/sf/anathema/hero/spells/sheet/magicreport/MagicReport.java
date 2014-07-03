package net.sf.anathema.hero.spells.sheet.magicreport;

import com.google.common.base.Joiner;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.charms.display.tooltip.CharmTypeContributor;
import net.sf.anathema.hero.charms.display.tooltip.ScreenDisplayInfoContributor;
import net.sf.anathema.hero.charms.display.tooltip.source.MagicSourceContributor;
import net.sf.anathema.hero.charms.sheet.content.CharmContentHelper;
import net.sf.anathema.hero.charms.sheet.content.stats.CharmStats;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.framework.reporting.ReportException;
import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.magic.description.MagicDescription;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.text.MultiColumnTextReport;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.hero.spells.sheet.content.SpellStats;

import static java.text.MessageFormat.format;

public class MagicReport extends AbstractPdfReport {

  private final Environment environment;
  private final IApplicationModel model;
  private final MagicPartFactory partFactory;

  public MagicReport(Environment environment, IApplicationModel model) {
    this.environment = environment;
    this.model = model;
    partFactory = new MagicPartFactory(new PdfReportUtils());
  }

  @Override
  public String toString() {
    return environment.getString("MagicReport.Name");
  }

  @Override
  public void performPrint(Hero hero, Document document, PdfWriter writer) throws ReportException {
    try {
      MultiColumnTextReport report = new MultiColumnTextReport(document, writer);
      String currentGroup = "";
      for (Charm charm : new CharmFetcher().getCharms(hero)) {
        report.startSimulation();
        printCharm(report, hero, currentGroup, charm);
        report.simulateAndReset();
        currentGroup = printCharm(report, hero, currentGroup, charm);
        report.printForReal();
      }
      for (Spell spell : getCurrentSpells(hero)) {
        report.startSimulation();
        printSpell(report, currentGroup, spell);
        report.simulateAndReset();
        currentGroup = printSpell(report, currentGroup, spell);
        report.printForReal();
      }
    } catch (DocumentException e) {
      throw new ReportException(e);
    }
  }

  private String printCharm(MultiColumnTextReport report, Hero hero, String currentGroup, Charm charm) throws DocumentException {
    CharmStats charmStats = createCharmStats(hero, charm);
    if (!currentGroup.equals(charmStats.getGroupName(environment))) {
      currentGroup = charmStats.getGroupName(environment);
      report.addElement(partFactory.createGroupTitle(currentGroup));
    }
    addMagicName(charm, report);
    addCharmData(charmStats, charm, report);
    addCharmDescription(charm, report);
    return currentGroup;
  }

  private String printSpell(MultiColumnTextReport report, String currentGroup, Spell spell) throws DocumentException {
    SpellStats spellStats = createSpellStats(spell);
    String nextGroupName = format("{0} {1}", spellStats.getType(environment), spellStats.getGroupName(environment));
    if (!currentGroup.equals(nextGroupName)) {
      currentGroup = nextGroupName;
      report.addElement(partFactory.createGroupTitle(currentGroup));
    }
    addMagicName(spell, report);
    addSpellCost(spell, report);
    addSpellTarget(spellStats, report);
    addCharmDescription(spell, report);
    return currentGroup;
  }

  private void addSpellCost(Spell charm, MultiColumnTextReport report) throws DocumentException {
    String costsLabel = environment.getString("MagicReport.Costs.Label") + ": ";
    String costsValue = new ScreenDisplayInfoContributor(environment).createCostString(charm);
    report.addElement(partFactory.createDataPhrase(costsLabel, costsValue));
  }

  private void addSpellTarget(SpellStats spellStats, MultiColumnTextReport report) throws DocumentException {
    String targetLabel = environment.getString("MagicReport.Target.Label") + ": ";
    String target = Joiner.on(", ").join(spellStats.getDetailStrings(environment));
    report.addElement(partFactory.createDataPhrase(targetLabel, target));
  }

  private void addMagicName(Magic magic, MultiColumnTextReport report) throws DocumentException {
    String charmName = new MagicDisplayLabeler(environment).getLabelForMagic(magic);
    report.addElement(partFactory.createCharmTitle(charmName));
  }

  private void addCharmData(CharmStats charmStats, Charm charm, MultiColumnTextReport report) throws DocumentException {
    PdfPTable table = partFactory.createDataTable();
    addCostsCell(charm, table);
    addTypeCell(charm, table);
    addKeywordsRow(charmStats, table);
    addDurationRow(charmStats, table);
    report.addElement(table);
  }

  private void addCostsCell(Charm charm, PdfPTable table) {
    String costsLabel = environment.getString("MagicReport.Costs.Label") + ": ";
    String costsValue = new ScreenDisplayInfoContributor(environment).createCostString(charm);
    table.addCell(partFactory.createDataCell(costsLabel, costsValue));
  }

  private void addTypeCell(Charm charm, PdfPTable table) {
    String typeLabel = environment.getString("MagicReport.Type.Label") + ": ";
    String typeValue = new CharmTypeContributor(environment).createTypeString(charm.getCharmType());
    table.addCell(partFactory.createDataCell(typeLabel, typeValue));
  }

  private void addKeywordsRow(CharmStats charmStats, PdfPTable table) {
    String keywords = Joiner.on(", ").join(charmStats.getDetailStrings(environment));
    String keywordsLabel = environment.getString("MagicReport.Keywords.Label") + ": ";
    table.addCell(partFactory.createDoubleDataCell(keywordsLabel, keywords));
  }

  private void addDurationRow(CharmStats charmStats, PdfPTable table) {
    String durationLabel = environment.getString("MagicReport.Duration.Label") + ": ";
    String durationString = charmStats.getDurationString(environment);
    table.addCell(partFactory.createDoubleDataCell(durationLabel, durationString));
  }

  private void addCharmDescription(Magic magic, MultiColumnTextReport report) throws DocumentException {
    MagicDescription charmDescription = getCharmDescription(magic);
    if (charmDescription.isEmpty()) {
      String sourceString = new MagicSourceContributor<>(environment).createSourceString(magic);
      String sourceReference = environment.getString("MagicReport.See.Source", sourceString);
      report.addElement(partFactory.createDescriptionParagraph(sourceReference));
    }
    for (String paragraph : charmDescription.getParagraphs()) {
      report.addElement(partFactory.createDescriptionParagraph(paragraph));
    }
  }

  private CharmStats createCharmStats(Hero hero, Charm charm) {
    return new CharmStats(charm, new CharmContentHelper(hero));
  }

  private SpellStats createSpellStats(Spell spell) {
    return new SpellStats(spell);
  }

  private MagicDescription getCharmDescription(Magic magic) {
    return CharmDescriptionProviderExtractor.CreateFor(model, environment).getCharmDescription(magic);
  }

  @Override
  public boolean supports(Hero hero) {
    return new CharmFetcher().hasCharms(hero);
  }

  private Spell[] getCurrentSpells(Hero hero) {
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    return SpellsModelFetcher.fetch(hero).getLearnedSpells(experienced);
  }
}