package net.sf.anathema.hero.concept.display.caste.presenter;

import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.text.ITextualDescription;
import net.sf.anathema.namegenerator.domain.INameGenerator;
import net.sf.anathema.namegenerator.domain.Names;

public class NameGeneratorCommand implements Command {
  private final ITextualDescription description;
  private final INameGenerator generator;

  public NameGeneratorCommand(ITextualDescription description, INameGenerator generator) {
    this.description = description;
    this.generator = generator;
  }

  @Override
  public void execute() {
    Names names = generator.createNames(1);
    description.setText(names.getFirst());
  }
}