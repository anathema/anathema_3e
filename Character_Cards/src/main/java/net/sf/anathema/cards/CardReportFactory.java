package net.sf.anathema.cards;

import net.sf.anathema.cards.data.providers.CharmCardDataProvider;
import net.sf.anathema.cards.data.providers.EquipmentCardDataProvider;
import net.sf.anathema.cards.data.providers.ICardDataProvider;
import net.sf.anathema.cards.data.providers.LegendCardDataProvider;
import net.sf.anathema.cards.data.providers.SpellCardDataProvider;
import net.sf.anathema.cards.layout.DemocritusCardLayout;
import net.sf.anathema.cards.layout.ICardLayout;
import net.sf.anathema.hero.environment.report.RegisteredReportFactory;
import net.sf.anathema.hero.environment.report.Report;
import net.sf.anathema.hero.environment.report.ReportFactory;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

import java.util.ArrayList;
import java.util.List;

@RegisteredReportFactory
@Weight(weight = 40)
public class CardReportFactory implements ReportFactory {
  @Override
  public Report[] createReport(Environment environment, ApplicationModel model) {
    List<ICardDataProvider> dataProviders = new ArrayList<>();
    dataProviders.add(new CharmCardDataProvider(model, environment));
    dataProviders.add(new SpellCardDataProvider(model, environment));
    dataProviders.add(new EquipmentCardDataProvider(environment));
    dataProviders.add(new LegendCardDataProvider(environment));
    ICardLayout layout = new DemocritusCardLayout(.23f, environment);
    return new Report[]{new CardReport(environment, layout, dataProviders.toArray(new ICardDataProvider[dataProviders.size()]))};
  }
}
