package net.sf.anathema.hero.framework.persistence;

import net.sf.anathema.hero.persistence.HeroModelLoader;
import net.sf.anathema.platform.repository.access.RepositoryReadAccess;

import java.io.InputStream;

public class HeroModelLoaderImpl implements HeroModelLoader {

  private RepositoryReadAccess readAccess;

  public HeroModelLoaderImpl(RepositoryReadAccess readAccess) {
    this.readAccess = readAccess;
  }

  @Override
  public InputStream openInputStream(String persistenceId) {
    return readAccess.openSubInputStream(persistenceId);
  }
}
