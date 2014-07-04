package net.sf.anathema.hero.concept.display.description;

import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.text.ITextualDescription;

public interface NameEditAction {
  void configure(Tool tool, ITextualDescription description);
}