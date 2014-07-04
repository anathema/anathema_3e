package net.sf.anathema.magic.description.module;

import net.sf.anathema.hero.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.magic.description.MagicDescriptionProviderFactory;
import net.sf.anathema.hero.magic.description.RegisteredMagicDescriptionProviderFactory;
import net.sf.anathema.magic.description.persistence.MagicDescriptionDataBase;
import net.sf.anathema.magic.description.persistence.RepositoryMagicDescriptionDataBase;
import net.sf.anathema.platform.frame.ApplicationModel;

@RegisteredMagicDescriptionProviderFactory
public class RepositoryMagicDescriptionProviderFactory implements MagicDescriptionProviderFactory {

  @Override
  public MagicDescriptionProvider create(ApplicationModel anathemaModel) {
    MagicDescriptionDataBase dataBase = RepositoryMagicDescriptionDataBase.CreateFrom(anathemaModel);
    return new RepositoryMagicDescriptionProvider(dataBase);
  }
}
