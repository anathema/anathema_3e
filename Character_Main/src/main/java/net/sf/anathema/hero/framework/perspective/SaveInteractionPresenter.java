package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.framework.perspective.model.ItemSelectionModel;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.interaction.model.Hotkey;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;

import java.io.IOException;

public class SaveInteractionPresenter {

  private final ItemSelectionModel model;
  private final Tool interaction;
  private Resources resources;

  public SaveInteractionPresenter(ItemSelectionModel model, Tool interaction, Resources resources) {
    this.model = model;
    this.interaction = interaction;
    this.resources = resources;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
    initializeCommand();
    interaction.setHotkey(new Hotkey('s'));
  }

  private void initializeAppearance() {
    interaction.setTooltip(resources.getString("AnathemaPersistence.SaveAction.Tooltip"));
    interaction.setIcon(new RelativePath("icons/TaskBarSave24.png"));
  }

  private void initializeEnabling() {
    model.whenCurrentSelectionBecomesDirty(new EnableInteraction(interaction));
    model.whenCurrentSelectionBecomesClean(new DisableInteraction(interaction));
    interaction.disable();
  }

  private void initializeCommand() {
    interaction.setCommand(new SaveCurrent());
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
