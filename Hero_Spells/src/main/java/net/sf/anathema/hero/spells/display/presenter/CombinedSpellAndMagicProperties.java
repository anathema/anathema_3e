package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.magic.display.magic.MagicLearnProperties;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.presenter.IdentifierConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;
import net.sf.anathema.platform.taskbar.BasicUi;

import java.util.List;

public class CombinedSpellAndMagicProperties implements SpellViewProperties, MagicLearnProperties {

  private final Resources resources;
  private final SpellsModel spellConfiguration;
  private final SpellTooltipBuilder tooltipBuilder;
  private final ExperienceModel experienceModel;

  public CombinedSpellAndMagicProperties(Resources resources,
                                         MagicDescriptionProvider magicDescriptionProvider, SpellsModel spellConfiguration, ExperienceModel experience) {
    this.resources = resources;
    this.spellConfiguration = spellConfiguration;
    this.tooltipBuilder = new SpellTooltipBuilder(resources, this, magicDescriptionProvider);
    this.experienceModel = experience;
  }


  @Override
  public RelativePath getAddButtonIcon() {
    return new BasicUi().getRightArrowIconPath();
  }

  @Override
  public RelativePath getRemoveButtonIcon() {
    return new BasicUi().getLeftArrowIconPath();
  }
  
  @Override
  public String getCircleLabel() {
    return resources.getString("CardView.Spells.Circle");
  }

  @Override
  public AgnosticUIConfiguration getMagicRenderer() {
    return new SpellUiConfiguration(resources, tooltipBuilder);
  }

  @Override
  public AgnosticUIConfiguration<Identifier> getCircleSelectionRenderer() {
    return new IdentifierConfiguration(resources);
  }

  @Override
  public boolean isMagicSelectionAvailable(List selection) {
    return !selection.isEmpty() && spellConfiguration.isSpellAllowed((Spell) selection.get(0));
  }

  @Override
  public String getAddButtonToolTip() {
    return resources.getString("CardView.Spells.AddToolTip");
  }

  @Override
  public String getRemoveButtonToolTip() {
    return resources.getString("CardView.Spells.RemoveToolTip");
  }

  @Override
  public boolean isRemoveAllowed(List list) {
    boolean enabled = !list.isEmpty();
    if (enabled && experienceModel.isExperienced()) {
      for (Object spellObject : list) {
        Spell spell = (Spell) spellObject;
        if (spellConfiguration.isLearnedOnCreation(spell)) {
          enabled = false;
          break;
        }
      }
    }
    return enabled;
  }
}