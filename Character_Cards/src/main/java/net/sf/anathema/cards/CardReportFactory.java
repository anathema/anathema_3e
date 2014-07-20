package net.sf.anathema.cards;

import net.sf.anathema.cards.data.providers.CharmCardDataProvider;
import net.sf.anathema.cards.data.providers.EquipmentCardDataProvider;
import net.sf.anathema.cards.data.providers.ICardDataProvider;
import net.sf.anathema.cards.data.providers.LegendCardDataProvider;
import net.sf.anathema.cards.data.providers.SpellCardDataProvider;
import net.sf.anathema.cards.layout.DemocritusCardLayout;
import net.sf.anathema.cards.layout.ICardLayout;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.report.RegisteredReportFactory;
import net.sf.anathema.hero.environment.report.Report;
import net.sf.anathema.hero.environment.report.ReportFactory;
import net.sf.anathema.library.initialization.Weight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RegisteredReportFactory
@Weight(weight = 40)
public class CardReportFactory implements ReportFactory {
  @Override
  public List<Report> createReport(HeroEnvironment environment) {
    List<ICardDataProvider> dataProviders = new ArrayList<>();
    dataProviders.add(new CharmCardDataProvider(environment));
    dataProviders.add(new SpellCardDataProvider(environment));
    dataProviders.add(new EquipmentCardDataProvider(environment.getResources()));
    dataProviders.add(new LegendCardDataProvider(environment.getResources()));
    ICardLayout layout = new DemocritusCardLayout(.23f, environment.getResources());
    return Collections.singletonList(new CardReport(environment.getResources(), layout, dataProviders));
  }
}
