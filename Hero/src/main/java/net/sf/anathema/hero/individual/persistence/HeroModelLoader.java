package net.sf.anathema.hero.individual.persistence;

import java.io.InputStream;

public interface HeroModelLoader {

  InputStream openInputStream(String persistenceId);
}
