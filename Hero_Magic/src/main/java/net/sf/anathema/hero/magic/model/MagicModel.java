package net.sf.anathema.hero.magic.model;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.magic.sheet.content.IMagicStats;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;

public class MagicModel implements HeroModel, PrintMagicProvider {

  public static final SimpleIdentifier ID = new SimpleIdentifier("Magic");
  private final List<PrintMagicProvider> printMagicProviders = new ArrayList<>();

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    //nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    //nothing to do
  }

  public void addPrintProvider(PrintMagicProvider provider) {
    printMagicProviders.add(provider);
  }

  @Override
  public void addPrintMagic(List<IMagicStats> printMagic) {
    for (PrintMagicProvider provider : printMagicProviders) {
      provider.addPrintMagic(printMagic);
    }
  }
}
