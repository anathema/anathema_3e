package net.sf.anathema.cards.data.providers;

import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.magic.description.model.MagicDescription;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

public abstract class AbstractMagicCardDataProvider implements ICardDataProvider {

  private final ApplicationModel model;
  private final Environment environment;

  protected AbstractMagicCardDataProvider(ApplicationModel model, Environment environment) {
    this.model = model;
    this.environment = environment;
  }

  protected Resources getResources() {
    return environment;
  }

  protected MagicDescription getMagicDescription(Magic magic) {
    return CharmDescriptionProviderExtractor.CreateFor(model, environment).getCharmDescription(magic);
  }
}
