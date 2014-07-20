package net.sf.anathema.cards.data.providers;

import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.hero.individual.model.Hero;

import java.util.List;

public interface ICardDataProvider {

	List<ICardData> getCards(Hero hero, ICardReportResourceProvider resourceProvider);
}
