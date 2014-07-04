package net.sf.anathema.framework.repository.preferences;

import net.sf.anathema.library.event.ObjectValueListener;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.text.ITextView;

import java.nio.file.Path;

public interface RepositoryPreferenceView {
  ITextView addRepositoryDisplay(String label);

  Tool addTool();

  void selectNewRepository(String prompt);

  void whenRepositoryChangeIsRequested(ObjectValueListener<Path> objectValueListener);

  void showInExplorer(Path repositoryPath);

  boolean canOpenInExplorer();
}
