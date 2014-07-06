package net.sf.anathema.cards.data.providers;

import com.itextpdf.text.Image;
import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.data.LegendCardData;
import net.sf.anathema.cards.data.LegendEntry;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.martial.MartialArtsUtilities;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.library.resources.Resources;

import java.util.ArrayList;
import java.util.List;

import static java.text.MessageFormat.format;

public class LegendCardDataProvider implements ICardDataProvider {

  private final Resources resources;

  public List<LegendEntry> traits = new ArrayList<>();
  public List<LegendEntry> characterTypes = new ArrayList<>();
  public List<LegendEntry> spellCircles = new ArrayList<>();
  public List<LegendEntry> martialArtLevels = new ArrayList<>();
  public List<LegendEntry> martialArtStyles = new ArrayList<>();
  public List<LegendEntry> misc = new ArrayList<>();

  public LegendCardDataProvider(Resources resources) {
    this.resources = resources;
  }

  @Override
  public ICardData[] getCards(Hero hero, ICardReportResourceProvider resourceProvider) {
    traits.clear();
    characterTypes.clear();
    spellCircles.clear();
    martialArtLevels.clear();
    martialArtStyles.clear();
    misc.clear();

    buildCharmEntries(resourceProvider, getCurrentCharms(hero));
    buildSpellEntries(resourceProvider, getCurrentSpells(hero));

    cleanEntries();

    return createCards(resourceProvider);
  }

  private ICardData[] createCards(ICardReportResourceProvider resourceProvider) {
    List<LegendEntry> entries = new ArrayList<>();
    List<ICardData> cards = new ArrayList<>();

    entries.addAll(traits);
    entries.addAll(spellCircles);
    entries.addAll(characterTypes);
    entries.addAll(martialArtLevels);
    entries.addAll(martialArtStyles);

    String legend = resources.getString("CardsReport.Legend");

    boolean newPage = true;

    while (!entries.isEmpty()) {
      List<LegendEntry> cardEntries = new ArrayList<>();
      for (int i = 0; i != LegendCardData.ICONS_PER_CARD && !entries.isEmpty(); i++) {
        if (entries.get(0).getIcon() != resourceProvider.getNullIcon()) {
          cardEntries.add(entries.get(0));
        }
        entries.remove(0);
      }

      // add low priority entries, if there is room
      while (entries.isEmpty() && !misc.isEmpty() && cardEntries.size() < LegendCardData.ICONS_PER_CARD) {
        cardEntries.add(misc.get(0));
        misc.remove(0);
      }

      if (cardEntries.size() > 0) {
        cards.add(new LegendCardData(resourceProvider, legend, cardEntries.toArray(new LegendEntry[cardEntries.size()]), newPage));
      }
      newPage = false;
    }

    return cards.toArray(new ICardData[cards.size()]);
  }

  private void buildCharmEntries(ICardReportResourceProvider resourceProvider, Charm[] charms) {
    for (Charm charm : charms) {
      if (!MartialArtsUtilities.isMartialArts(charm)) {
        LegendEntry trait = new LegendEntry(resourceProvider.getTreeIcon(charm), resourceProvider.getTreeLabel(charm));
        if (!traits.contains(trait)) {
          traits.add(trait);
        }
        LegendEntry character = new LegendEntry(resourceProvider.getCategoryIcon(charm), resourceProvider.getCategoryLabel(charm));
        if (!characterTypes.contains(character)) {
          characterTypes.add(character);
        }
      } else {
        LegendEntry level = new LegendEntry(resourceProvider.getCategoryIcon(charm), resourceProvider.getCategoryLabel(charm));
        if (!martialArtLevels.contains(level)) {
          martialArtLevels.add(level);
        }
        Image styleIcon = resourceProvider.getTreeIcon(charm);
        if (styleIcon != null) {
          String styleString = resourceProvider.getTreeLabel(charm);
          LegendEntry styleEntry = new LegendEntry(styleIcon, styleString);
          if (!martialArtStyles.contains(styleEntry)) {
            martialArtStyles.add(styleEntry);
          }
        }
      }
    }
  }

  private void buildSpellEntries(ICardReportResourceProvider resourceProvider, Spell[] spells) {
    for (Spell spell : spells) {

      String circleString;
      String circleFullString = spell.getCircleType().isSorceryCircle() ? resources.getString("CardsReport.Legend.Sorcery") :
                                resources.getString("CardsReport.Legend.Necromancy");
      circleString = format(circleFullString, resources.getString(spell.getCircleType().getId()));
      LegendEntry circle = new LegendEntry(resourceProvider.getSpellIcon(spell.getCircleType()), circleString);
      if (!spellCircles.contains(circle)) {
        spellCircles.add(circle);
      }
    }
  }

  private void cleanEntries() {
    // reshuffle some entries around to minimize card count

    // if we only have one character type, deprioritize it
    if (martialArtLevels.size() <= 1) {
      misc.addAll(martialArtLevels);
      martialArtLevels.clear();
    }

    // if we only have one character type, don't bother to print it
    if (characterTypes.size() == 1) {
      misc.addAll(characterTypes);
      characterTypes.clear();
    }
  }

  private Charm[] getCurrentCharms(Hero hero) {
    return CharmsModelFetcher.fetch(hero).getLearnedCharms();
  }

  private Spell[] getCurrentSpells(Hero hero) {
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    return SpellsModelFetcher.fetch(hero).getLearnedSpells(experienced);
  }

}
