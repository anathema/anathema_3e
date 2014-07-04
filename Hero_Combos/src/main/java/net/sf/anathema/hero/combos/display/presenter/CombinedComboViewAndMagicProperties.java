package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.application.CharacterUI;
import net.sf.anathema.hero.charms.display.magic.AbstractMagicLearnProperties;
import net.sf.anathema.hero.charms.display.tooltip.CharmTooltipBuilder;
import net.sf.anathema.hero.charms.display.tooltip.CharmTooltipBuilderImpl;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;
import net.sf.anathema.platform.taskbar.BasicUi;

import java.util.List;

public class CombinedComboViewAndMagicProperties extends AbstractMagicLearnProperties implements ComboViewProperties {
  private final CombosModel comboConfiguration;
  private final CharmTooltipBuilder charmInfoStringProvider;

  CombinedComboViewAndMagicProperties(Resources resources, CombosModel comboConfiguration, MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    this.charmInfoStringProvider = new CharmTooltipBuilderImpl(getResources(), magicDescriptionProvider);
    this.comboConfiguration = comboConfiguration;
  }

  @Override
  public RelativePath getFinalizeButtonIcon() {
    return new CharacterUI().getFinalizeIconPath();
  }

  @Override
  public boolean isMagicSelectionAvailable(List list) {
    return !list.isEmpty() && comboConfiguration.isComboLegal((Charm) list.get(0));
  }

  @Override
  public boolean isRemoveAllowed(List list) {
    return !list.isEmpty();
  }

  @Override
  public AgnosticUIConfiguration getMagicRenderer() {
    return new CharmUiConfigurationWithoutSpecials(getResources(), charmInfoStringProvider);
  }

  @Override
  public RelativePath getClearButtonIcon() {
    return new BasicUi().getClearIconPath();
  }

  @Override
  public String getFinalizeButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.FinalizeToolTip");
  }

  @Override
  public String getClearButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.ClearToolTip");
  }

  @Override
  public String getAddButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.AddToolTip");
  }

  @Override
  public String getRemoveButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.RemoveToolTip");
  }

  @Override
  public RelativePath getCancelEditButtonIcon() {
    return new CharacterUI().getCancelComboEditIconPath();
  }

  @Override
  public String getFinalizeButtonEditToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.FinalizeEditToolTip");
  }

  @Override
  public String getCancelButtonEditToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.ClearEditToolTip");
  }
}