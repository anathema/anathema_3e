package net.sf.anathema.magic.description.module;

import net.sf.anathema.hero.application.environment.Inject;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;
import net.sf.anathema.magic.description.model.MagicDescriptionProviderFactory;
import net.sf.anathema.magic.description.model.RegisteredMagicDescriptionProviderFactory;
import net.sf.anathema.magic.description.persistence.MagicDescriptionDataBase;
import net.sf.anathema.magic.description.persistence.RepositoryMagicDescriptionDataBase;
import net.sf.anathema.platform.frame.ApplicationModel;

@RegisteredMagicDescriptionProviderFactory
public class RepositoryMagicDescriptionProviderFactory implements MagicDescriptionProviderFactory {

  @Inject
  public ApplicationModel applicationModel;

  @Override
  public MagicDescriptionProvider create(HeroEnvironment environment) {
    MagicDescriptionDataBase dataBase = RepositoryMagicDescriptionDataBase.CreateFrom(applicationModel);
    return new RepositoryMagicDescriptionProvider(dataBase);
  }
}
