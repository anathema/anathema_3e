package net.sf.anathema.magic.description.module;

import net.sf.anathema.hero.application.Inject2;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;
import net.sf.anathema.magic.description.model.MagicDescriptionProviderFactory;
import net.sf.anathema.magic.description.model.RegisteredMagicDescriptionProviderFactory;
import net.sf.anathema.magic.description.persistence.MagicDescriptionDataBase;
import net.sf.anathema.magic.description.persistence.RepositoryMagicDescriptionDataBase;
import net.sf.anathema.platform.frame.ApplicationModelImpl;

@RegisteredMagicDescriptionProviderFactory
public class RepositoryMagicDescriptionProviderFactory implements MagicDescriptionProviderFactory {

  @Inject2
  public ApplicationModelImpl applicationModel;

  @Override
  public MagicDescriptionProvider create(HeroEnvironment environment) {
    MagicDescriptionDataBase dataBase = RepositoryMagicDescriptionDataBase.CreateFrom(applicationModel);
    return new RepositoryMagicDescriptionProvider(dataBase);
  }
}
