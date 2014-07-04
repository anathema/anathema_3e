package net.sf.anathema.hero.concept.display.description;

import net.sf.anathema.hero.concept.display.caste.presenter.NameGeneratorCommand;
import net.sf.anathema.hero.framework.CharacterUI;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextualDescription;
import net.sf.anathema.namegenerator.domain.realm.RealmNameGenerator;

@Weight(weight = 1)
@RegisteredNameEditAction
public class RealmNameAction implements NameEditAction {

  private Resources resources;

  public RealmNameAction(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void configure(Tool tool, ITextualDescription description) {
    tool.setIcon(new CharacterUI().getRandomRealmNameIconPath());
    tool.setTooltip(resources.getString("CharacterDescription.Tooltip.RealmName"));
    tool.setCommand(new NameGeneratorCommand(description, new RealmNameGenerator()));
  }
}
