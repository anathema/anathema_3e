package net.sf.anathema.hero.application.persistence;

import java.io.OutputStream;

public interface HeroModelSaver {

  OutputStream openOutputStream(String persistenceId);
}
