package net.sf.anathema.magic.description.module;

import net.sf.anathema.hero.application.Inject2;
import net.sf.anathema.hero.application.environment.Inject;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.magic.description.model.MagicDescription;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;
import net.sf.anathema.magic.description.model.MagicDescriptionProviderFactory;
import net.sf.anathema.magic.description.model.RegisteredMagicDescriptionProviderFactory;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.frame.ApplicationModelImpl;

@RegisteredMagicDescriptionProviderFactory
public class RepositoryMagicDescriptionProviderFactory implements MagicDescriptionProviderFactory {

  @Inject2
  public ApplicationModelImpl applicationModel;

  public RepositoryMagicDescriptionProviderFactory() {
    this.applicationModel =null;
    new RuntimeException().printStackTrace();
  }

  @Override
  public MagicDescriptionProvider create(HeroEnvironment environment) {
    //MagicDescriptionDataBase dataBase = RepositoryMagicDescriptionDataBase.CreateFrom(applicationModel);
    //return new RepositoryMagicDescriptionProvider(dataBase);
    // todo sandra InjectionFactory does not work correctly
    return new MagicDescriptionProvider() {
      @Override
      public MagicDescription getCharmDescription(Magic magic) {
        return null;
      }
    };
  }

  @Inject
  public void setModel(ApplicationModel model) {
    this.applicationModel = (ApplicationModelImpl) model;
  }
}
