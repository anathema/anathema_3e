package net.sf.anathema.hero.environment.report;

import net.sf.anathema.hero.individual.model.Hero;

import java.io.OutputStream;

public interface Report {

  boolean supports(Hero hero);

  void print(String name, Hero hero, OutputStream stream) throws ReportException;
}
