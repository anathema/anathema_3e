package net.sf.anathema.hero.spells.sheet.magicreport;

import com.google.common.base.Joiner;
import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.charms.display.tooltip.ScreenDisplayInfoContributor;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.text.MultiColumnTextReport;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.hero.spells.sheet.content.SpellStats;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.resources.Resources;

import static java.text.MessageFormat.format;

@Weight(weight = 20)
public class SpellPrinter implements MagicPrinter{
  private final HeroEnvironment environment;
  private final Resources resources;
  private final MagicPartFactory partFactory;

  public SpellPrinter(MagicPartFactory partFactory, HeroEnvironment environment) {
    this.environment = environment;
    this.resources = environment.getResources();
    this.partFactory = partFactory;
  }

  @Override
  public void print(MultiColumnTextReport report, Hero hero) throws DocumentException {
    String currentGroup = "";
    for (Spell spell : getCurrentSpells(hero)) {
      report.startSimulation();
      printSpell(report, currentGroup, spell);
      report.simulateAndReset();
      currentGroup = printSpell(report, currentGroup, spell);
      report.printForReal();
    }
  }

  @Override
  public boolean hasData(Hero hero) {
    return !getCurrentSpells(hero).isEmpty();
  }

  private String printSpell(MultiColumnTextReport report, String currentGroup, Spell spell) throws DocumentException {
    SpellStats spellStats = createSpellStats(spell);
    String nextGroupName = format("{0} {1}", spellStats.getType(resources), spellStats.getGroupName(resources));
    if (!currentGroup.equals(nextGroupName)) {
      currentGroup = nextGroupName;
      report.addElement(partFactory.createGroupTitle(currentGroup));
    }
    MagicPrintSupport magicPrintSupport = new MagicPrintSupport(resources, partFactory, environment);
    magicPrintSupport.addMagicName(spell, report);
    addSpellCost(spell, report);
    addSpellTarget(spellStats, report);
    magicPrintSupport.addCharmDescription(spell, report);
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

  private SpellStats createSpellStats(Spell spell) {
    return new SpellStats(spell);
  }

  private Spells getCurrentSpells(Hero hero) {
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    return SpellsModelFetcher.fetch(hero).getLearnedSpells(experienced);
  }
}
