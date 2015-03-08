package net.sf.anathema.hero.spells.sheet.magicreport;

import com.google.common.base.Joiner;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.hero.charms.display.tooltip.CharmTypeContributor;
import net.sf.anathema.hero.magic.display.tooltip.ScreenDisplayInfoContributor;
import net.sf.anathema.hero.charms.sheet.content.CharmContentHelper;
import net.sf.anathema.hero.charms.sheet.content.stats.CharmStats;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.text.MultiColumnTextReport;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.data.Charm;

@Weight(weight = 10)
public class CharmPrinter implements MagicPrinter{
  private final Resources resources;
  private final MagicPartFactory partFactory;
  private final MagicPrintSupport magicPrintSupport;

  public CharmPrinter( MagicPartFactory partFactory, HeroEnvironment environment) {
    this.resources = environment.getResources();
    this.partFactory = partFactory;
    this.magicPrintSupport = new MagicPrintSupport(resources, partFactory, environment);
  }

  @Override
  public boolean hasData(Hero hero) {
    return new CharmFetcher().hasCharms(hero);
  }

  @Override
  public void print(MultiColumnTextReport report, Hero hero) throws DocumentException {
    String currentGroup = "";
    for (Charm charm : new CharmFetcher().getCharms(hero)) {
      report.startSimulation();
      printCharm(report, hero, currentGroup, charm);
      report.simulateAndReset();
      currentGroup = printCharm(report, hero, currentGroup, charm);
      report.printForReal();
    }
  }

  private String printCharm(MultiColumnTextReport report, Hero hero, String currentGroup, Charm charm) throws DocumentException {
    CharmStats charmStats = createCharmStats(hero, charm);
    if (!currentGroup.equals(charmStats.getGroupName(resources))) {
      currentGroup = charmStats.getGroupName(resources);
      report.addElement(partFactory.createGroupTitle(currentGroup));
    }
    magicPrintSupport.addMagicName(charm, report);
    addCharmData(charmStats, charm, report);
    magicPrintSupport.addMagicDescription(charm, report);
    return currentGroup;
  }

  private CharmStats createCharmStats(Hero hero, Charm charm) {
    return new CharmStats(charm, new CharmContentHelper(hero));
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
}