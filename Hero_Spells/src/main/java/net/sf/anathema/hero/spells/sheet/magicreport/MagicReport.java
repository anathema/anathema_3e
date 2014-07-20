package net.sf.anathema.hero.spells.sheet.magicreport;

import com.google.common.base.Joiner;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.charms.display.tooltip.CharmTypeContributor;
import net.sf.anathema.hero.charms.display.tooltip.ScreenDisplayInfoContributor;
import net.sf.anathema.hero.charms.display.tooltip.source.MagicSourceContributor;
import net.sf.anathema.hero.charms.sheet.content.CharmContentHelper;
import net.sf.anathema.hero.charms.sheet.content.stats.CharmStats;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.report.ReportException;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.text.MultiColumnTextReport;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.hero.spells.sheet.content.SpellStats;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.magic.description.model.MagicDescription;

import static java.text.MessageFormat.format;

public class MagicReport extends AbstractPdfReport {

  private final MagicPartFactory partFactory;
  private final Resources resources;
  private HeroEnvironment environment;

  public MagicReport(HeroEnvironment environment) {
    this.environment = environment;
    this.resources = environment.getResources();
    partFactory = new MagicPartFactory(new PdfReportUtils());
  }

  @Override
  public String toString() {
    return resources.getString("MagicReport.Name");
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
    if (!currentGroup.equals(charmStats.getGroupName(resources))) {
      currentGroup = charmStats.getGroupName(resources);
      report.addElement(partFactory.createGroupTitle(currentGroup));
    }
    addMagicName(charm, report);
    addCharmData(charmStats, charm, report);
    addCharmDescription(charm, report);
    return currentGroup;
  }

  private String printSpell(MultiColumnTextReport report, String currentGroup, Spell spell) throws DocumentException {
    SpellStats spellStats = createSpellStats(spell);
    String nextGroupName = format("{0} {1}", spellStats.getType(resources), spellStats.getGroupName(resources));
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
    String costsLabel = resources.getString("MagicReport.Costs.Label") + ": ";
    String costsValue = new ScreenDisplayInfoContributor(resources).createCostString(charm);
    report.addElement(partFactory.createDataPhrase(costsLabel, costsValue));
  }

  private void addSpellTarget(SpellStats spellStats, MultiColumnTextReport report) throws DocumentException {
    String targetLabel = resources.getString("MagicReport.Target.Label") + ": ";
    String target = Joiner.on(", ").join(spellStats.getDetailStrings(resources));
    report.addElement(partFactory.createDataPhrase(targetLabel, target));
  }

  private void addMagicName(Magic magic, MultiColumnTextReport report) throws DocumentException {
    String charmName = new MagicDisplayLabeler(resources).getLabelForMagic(magic);
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
    String costsLabel = resources.getString("MagicReport.Costs.Label") + ": ";
    String costsValue = new ScreenDisplayInfoContributor(resources).createCostString(charm);
    table.addCell(partFactory.createDataCell(costsLabel, costsValue));
  }

  private void addTypeCell(Charm charm, PdfPTable table) {
    String typeLabel = resources.getString("MagicReport.Type.Label") + ": ";
    String typeValue = new CharmTypeContributor(resources).createTypeString(charm.getCharmType());
    table.addCell(partFactory.createDataCell(typeLabel, typeValue));
  }

  private void addKeywordsRow(CharmStats charmStats, PdfPTable table) {
    String keywords = Joiner.on(", ").join(charmStats.getDetailStrings(resources));
    String keywordsLabel = resources.getString("MagicReport.Keywords.Label") + ": ";
    table.addCell(partFactory.createDoubleDataCell(keywordsLabel, keywords));
  }

  private void addDurationRow(CharmStats charmStats, PdfPTable table) {
    String durationLabel = resources.getString("MagicReport.Duration.Label") + ": ";
    String durationString = charmStats.getDurationString(resources);
    table.addCell(partFactory.createDoubleDataCell(durationLabel, durationString));
  }

  private void addCharmDescription(Magic magic, MultiColumnTextReport report) throws DocumentException {
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

  private CharmStats createCharmStats(Hero hero, Charm charm) {
    return new CharmStats(charm, new CharmContentHelper(hero));
  }

  private SpellStats createSpellStats(Spell spell) {
    return new SpellStats(spell);
  }

  private MagicDescription getCharmDescription(Magic magic) {
    return CharmDescriptionProviderExtractor.CreateFor(environment).getCharmDescription(magic);
  }

  @Override
  public boolean supports(Hero hero) {
    return new CharmFetcher().hasCharms(hero);
  }

  private Spells getCurrentSpells(Hero hero) {
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    return SpellsModelFetcher.fetch(hero).getLearnedSpells(experienced);
  }
}