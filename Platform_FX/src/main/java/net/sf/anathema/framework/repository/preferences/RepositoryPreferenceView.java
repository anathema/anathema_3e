package net.sf.anathema.framework.repository.preferences;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.library.event.ObjectChangedListener;

import java.nio.file.Path;

public interface RepositoryPreferenceView {
  ITextView addRepositoryDisplay(String label);

  Tool addTool();

  void selectNewRepository(String prompt);

  void whenRepositoryChangeIsRequested(ObjectChangedListener<Path> objectChangedListener);

  void showInExplorer(Path repositoryPath);

  boolean canOpenInExplorer();
}
