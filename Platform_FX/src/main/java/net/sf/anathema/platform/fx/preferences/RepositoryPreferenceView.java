package net.sf.anathema.platform.fx.preferences;

import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.text.ITextView;

import java.nio.file.Path;

public interface RepositoryPreferenceView {
  ITextView addRepositoryDisplay(String label);

  Tool addTool();

  void selectNewRepository(String prompt);

  void whenRepositoryChangeIsRequested(ObjectChangedListener<Path> objectChangedListener);

  void showInExplorer(Path repositoryPath);

  boolean canOpenInExplorer();
}
