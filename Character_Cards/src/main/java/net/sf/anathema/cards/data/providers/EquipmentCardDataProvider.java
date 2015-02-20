package net.sf.anathema.cards.data.providers;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.cards.data.EquipmentCardData;
import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.presentation.EquipmentStringBuilder;
import net.sf.anathema.equipment.presentation.IEquipmentStringBuilder;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.resources.Resources;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

public class EquipmentCardDataProvider implements ICardDataProvider {

  private final Resources resources;
  private final IEquipmentStringBuilder stringBuilder;
  // value of black circle character in standard symbol font
  private final static char SYMBOL_BLACK_CIRCLE = (char) 183;

  public EquipmentCardDataProvider(Resources resources) {
    this.resources = resources;
    this.stringBuilder = new EquipmentStringBuilder(resources);
  }

  @Override
  public List<ICardData> getCards(Hero hero, ICardReportResourceProvider resourceProvider) {
    EquipmentModel model = EquipmentModelFetcher.fetch(hero);
    List<ICardData> data = new ArrayList<>();
    for (IEquipmentItem item : model.getEquipmentItems()) {
      String title = item.getTitle();
      Paragraph headerText = new Paragraph();
      if (hasCustomTitle(item)) {
        headerText.add(new Phrase(item.getTemplateId(), resourceProvider.getNormalFont()));
      }
      if (!headerText.isEmpty()) {
        headerText.add(new Phrase("\n"));
      }
      if (item.getCost() != null) {
        String[] costSegments = item.getCost().toString().split(" ");
        costSegments[1] = costSegments[1].replace('*', SYMBOL_BLACK_CIRCLE);

        headerText.add(new Phrase(costSegments[0] + " ", resourceProvider.getNormalFont()));
        headerText.add(new Phrase(costSegments[1], resourceProvider.getSymbolFont()));
      }

      List<Phrase> bodyText = new ArrayList<>();

      String description = item.getDescription();
      if (description != null && !description.isEmpty()) {
        bodyText.add(new Phrase(description, resourceProvider.getNormalFont()));
        bodyText.add(new Phrase("\n", resourceProvider.getNormalFont()));
      }
      for (IEquipmentStats stats : item.getStats()) {
        Paragraph statsParagraph = new Paragraph();
        /*if (stats instanceof ArtifactStats) {
          ArtifactStats artifactStats = (ArtifactStats) stats;
          if (artifactStats.getAttuneType() != ArtifactAttuneType.Attuned) {
            continue;
          }
          statsParagraph.add(new Phrase(resources.getString("Equipment.Stats.Short.AttuneCost").trim() + ": ", resourceProvider.getBoldFont()));
          statsParagraph.add(new Phrase(artifactStats.getAttuneCost() + "m", resourceProvider.getNormalFont()));
        } else {
          String statsString = stringBuilder.createString(item, stats);
          statsParagraph.add(new Phrase(stats.getId() + ": ", resourceProvider.getBoldFont()));
          statsParagraph.add(new Phrase(statsString.substring(statsString.indexOf(':') + 2), resourceProvider.getNormalFont()));
        }*/

        bodyText.add(statsParagraph);
      }

      data.add(new EquipmentCardData(title, headerText, bodyText.toArray(new Phrase[bodyText.size()]), resourceProvider.getNullIcon()));
    }
    return data;
  }

  private boolean hasCustomTitle(IEquipmentItem item) {
    return !item.getTemplateId().equals(item.getTitle());
  }

}
