package net.sf.anathema.cards.data.providers;

import net.sf.anathema.cards.data.CharmCardData;
import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.sheet.content.CharmContentHelper;
import net.sf.anathema.hero.charms.sheet.content.stats.CharmStats;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class CharmCardDataProvider extends AbstractMagicCardDataProvider {

  public CharmCardDataProvider(HeroEnvironment environment) {
    super(environment);
  }

  @Override
  public ICardData[] getCards(Hero hero, ICardReportResourceProvider fontProvider) {
    List<ICardData> cards = new ArrayList<>();
    for (Charm charm : CharmsModelFetcher.fetch(hero).getLearningModel().getCurrentlyLearnedCharms()) {
      cards.add(new CharmCardData(charm, createCharmStats(hero, charm), getMagicDescription(charm), fontProvider,
        getResources()));
    }
    return cards.toArray(new ICardData[cards.size()]);
  }

  private CharmStats createCharmStats(Hero hero, Charm charm) {
    return new CharmStats(charm, new CharmContentHelper(hero));
  }
}