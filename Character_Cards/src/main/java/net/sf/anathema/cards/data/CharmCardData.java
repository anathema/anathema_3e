package net.sf.anathema.cards.data;

import com.google.common.base.Joiner;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.display.tooltip.CharmTypeContributor;
import net.sf.anathema.hero.charms.sheet.content.stats.CharmStats;
import net.sf.anathema.hero.magic.description.MagicDescription;
import net.sf.anathema.library.resources.Resources;

public class CharmCardData extends AbstractMagicCardData {
  private Charm charm;
  private CharmStats stats;

  public CharmCardData(Charm charm, CharmStats stats, MagicDescription description, ICardReportResourceProvider fontProvider, Resources resources) {
    super(charm, description, fontProvider, resources);
    this.charm = charm;
    this.stats = stats;
  }

  @Override
  public Image getPrimaryIcon() {
    return getResourceProvider().getTreeIcon(charm);
  }

  @Override
  public Image getSecondaryIcon() {
    return getResourceProvider().getCategoryIcon(charm);
  }

  @Override
  public Paragraph getStats() {
    Paragraph stats = new Paragraph();
    stats.add(getCharmType(charm));
    stats.add("\n");
    stats.add(getCharmDuration(charm));
    return stats;
  }

  @Override
  public Element[] getBody(int contentHeight) {
    Paragraph phrases = new Paragraph();
    if (hasCost(charm)) {
      phrases.add(getCostPhrase(hasDescription()));
    }
    if (hasDescription()) {
      addDescriptionPhrases(phrases);
    }
    return new Element[]{phrases};
  }

  private Phrase getCharmType(Charm charm) {
    String type = new CharmTypeContributor(getResources()).createTypeString(charm.getCharmType());
    return new Phrase(8, type, getResourceProvider().getBoldFont());
  }

  private Phrase getCharmDuration(Charm charm) {
    String duration = charm.getDuration().getText();
    return new Phrase(duration, getResourceProvider().getNormalFont());
  }

  @Override
  public String getKeywords() {
    return Joiner.on(", ").join(stats.getDetailStrings(getResources()));
  }
}
