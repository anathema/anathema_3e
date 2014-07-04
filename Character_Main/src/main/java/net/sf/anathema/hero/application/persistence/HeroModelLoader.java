package net.sf.anathema.hero.application.persistence;

import java.io.InputStream;

public interface HeroModelLoader {

  InputStream openInputStream(String persistenceId);
}
