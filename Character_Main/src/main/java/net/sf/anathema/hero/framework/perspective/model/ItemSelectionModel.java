package net.sf.anathema.hero.framework.perspective.model;

import net.sf.anathema.hero.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.framework.reporting.Report;
import net.sf.anathema.lib.gui.file.SingleFileChooser;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.platform.environment.Environment;

import java.io.IOException;

public interface ItemSelectionModel {

  void saveCurrent() throws IOException;

  void whenCurrentSelectionBecomesDirty(ChangeListener listener);

  void whenCurrentSelectionBecomesClean(ChangeListener listener);

  void whenGetsSelection(ChangeListener listener);

  void whenCurrentSelectionBecomesExperienced(ChangeListener listener);

  void whenCurrentSelectionBecomesInexperienced(ChangeListener listener);

  void convertCurrentToExperienced();

  void printCurrentItemQuickly(Environment environment);

  void printCurrentItemInto(Report report, Environment environment, SingleFileChooser fileChooser);

  void createNew(CharacterTemplateCreator factory, Environment environment);

  void whenNewCharacterIsAdded(NewCharacterListener listener);

  void registerAllReportsOn(ReportRegister register, Environment environment);
}