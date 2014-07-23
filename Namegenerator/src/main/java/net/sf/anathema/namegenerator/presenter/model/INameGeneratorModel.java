package net.sf.anathema.namegenerator.presenter.model;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.namegenerator.domain.Names;

import java.util.Set;

public interface INameGeneratorModel {

  void addGeneratorTypeChangeListener(ChangeListener listener);

  Set<Identifier> getGeneratorTypes();

  Identifier getSelectedGeneratorType();

  void setGeneratorType(Identifier selectedGeneratorType);

  Names generateNames(int count);
}