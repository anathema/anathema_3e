package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.ItemSelectionModel;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.interaction.model.Hotkey;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;

import java.io.IOException;

public class SaveInteractionPresenter {

  private final ItemSelectionModel model;
  private final Tool tool;
  private Resources resources;

  public SaveInteractionPresenter(ItemSelectionModel model, Tool tool, Resources resources) {
    this.model = model;
    this.tool = tool;
    this.resources = resources;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
    initializeCommand();
    tool.setHotkey(new Hotkey('s'));
  }

  private void initializeAppearance() {
    tool.setTooltip(resources.getString("AnathemaPersistence.SaveAction.Tooltip"));
    tool.setText(resources.getString("AnathemaPersistence.SaveAction.Name"));
    tool.setIcon(new RelativePath("icons/TaskBarSave24.png"));
  }

  private void initializeEnabling() {
    model.whenCurrentSelectionBecomesDirty(new EnableInteraction(tool));
    model.whenCurrentSelectionBecomesClean(new DisableInteraction(tool));
    tool.disable();
  }

  private void initializeCommand() {
    tool.setCommand(new SaveCurrent());
  }

  private class SaveCurrent implements Command {
    @Override
    public void execute() {
      try {
        model.saveCurrent();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
