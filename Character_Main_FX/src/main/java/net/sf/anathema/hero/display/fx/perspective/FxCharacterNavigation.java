package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.perspective.CharacterButtonDto;
import net.sf.anathema.hero.application.perspective.CharacterGridView;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;
import net.sf.anathema.library.interaction.view.InteractionView;
import net.sf.anathema.library.interaction.view.MenuTool;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.fx.navigation.Navigation;

public class FxCharacterNavigation extends Navigation implements InteractionView, CharacterGridView {
  private final CharacterGridFxView gridView;

  public FxCharacterNavigation(UiEnvironment uiEnvironment) {
    super(uiEnvironment);
    this.gridView = new CharacterGridFxView(uiEnvironment);
    addContainerToNavigation(gridView.getNode());
  }


  @Override
  public void addButton(final CharacterButtonDto dto, final Selector<CharacterIdentifier> characterSelector) {
    gridView.addButton(dto, characterSelector);
  }

  @Override
  public void selectButton(final CharacterIdentifier identifier) {
    gridView.selectButton(identifier);
  }

  @Override
  public void updateButton(final CharacterButtonDto dto) {
    gridView.updateButton(dto);
  }

  @Override
  public CharacterTemplateCreator createNewCharacter() {
    return gridView.createNewCharacter();
  }

  @Override
  public MenuTool addMenuTool() {
    FxMenuButtonTool tool = FxMenuButtonTool.ForToolbar();
    addTool(tool);
    return tool;
  }
}
