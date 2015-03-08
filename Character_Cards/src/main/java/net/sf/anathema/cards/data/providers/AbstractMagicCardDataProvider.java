package net.sf.anathema.cards.data.providers;

import net.sf.anathema.hero.magic.display.presenter.MagicDescriptionProviderExtractor;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.magic.description.model.MagicDescription;

public abstract class AbstractMagicCardDataProvider implements ICardDataProvider {

  private final HeroEnvironment environment;

  protected AbstractMagicCardDataProvider(HeroEnvironment environment) {
    this.environment = environment;
  }

  protected Resources getResources() {
    return environment.getResources();
  }

  protected MagicDescription getMagicDescription(Magic magic) {
    return MagicDescriptionProviderExtractor.CreateFor(environment).getCharmDescription(magic);
  }
}
