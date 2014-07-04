package net.sf.anathema.hero.individual.persistence;

import java.io.OutputStream;

public interface HeroModelSaver {

  OutputStream openOutputStream(String persistenceId);
}
