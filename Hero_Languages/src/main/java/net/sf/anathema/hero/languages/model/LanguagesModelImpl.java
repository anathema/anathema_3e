package net.sf.anathema.hero.languages.model;

import com.google.common.collect.Lists;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.library.identifier.Identifier;

public class LanguagesModelImpl implements LanguagesModel {

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    MeritsModel meritsModel = MeritsModelFetcher.fetch(hero);
    meritsModel.addSuggestions(LANGUAGE_MERIT, Lists.newArrayList("High Realm", "Low Realm", "Old Realm", "Riverspeak", "Skytongue", "Flametongue", "Forest-tongue", "Seatongue", "Guild Cant", "Up to 4 Local Tongues, please specify"));
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    //nothing to do
  }
}