package net.sf.anathema.library.interaction.model;

import net.sf.anathema.library.resources.RelativePath;

public interface Tool {

  void setIcon(RelativePath relativePath);

  void setOverlay(RelativePath relativePath);

  void setTooltip(String text);

  void setText(String text);

  void enable();

  void disable();

  void setCommand(Command command);

  void setHotkey(Hotkey s);
}