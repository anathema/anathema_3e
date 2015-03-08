package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.hero.charms.display.tooltip.MagicDescriptionContributor;
import net.sf.anathema.hero.magic.display.tooltip.ScreenDisplayInfoContributor;
import net.sf.anathema.hero.magic.display.tooltip.source.MagicSourceContributor;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.tooltip.ConfigurableTooltip;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;

import com.google.common.base.Joiner;

public class SpellTooltipBuilder {

  private final Resources resources;
  private final CombinedSpellAndMagicProperties properties;
  private MagicDescriptionProvider magicDescriptionProvider;

  public SpellTooltipBuilder(Resources resources, CombinedSpellAndMagicProperties properties, MagicDescriptionProvider magicDescriptionProvider) {
    this.resources = resources;
    this.properties = properties;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public void createTooltip(Spell spell, ConfigurableTooltip tooltip) {
    tooltip.appendTitleLine(resources.getString(spell.getName().text));
    tooltip.appendLine(properties.getCircleLabel(), getCircleValue(spell));
    tooltip.appendLine(getCostLabel(), getCostValue(spell));
    tooltip.appendLine(getKeywordsLabel(), getKeywords(spell));
    tooltip.appendLine(getDurationLabel(), getDurationValue(spell));
    tooltip.appendLine(getSourceLabel(), getSourceValue(spell));
    new MagicDescriptionContributor(magicDescriptionProvider).buildStringForMagicWithoutSpecials(tooltip, spell);
  }

  private String getKeywords(Spell spell) {
  	if (spell.getKeywords().isEmpty()) {
  		return resources.getString("Keyword.None");
  	}
		return Joiner.on(", ").join(spell.getKeywords().stream().map(keyword ->
			resources.getString("Keyword." + keyword)).iterator());
	}

	private String getCircleValue(Spell spell) {
    return resources.getString(spell.getCircleType().getId());
  }

  private String getCostValue(Spell spell) {
    return new ScreenDisplayInfoContributor(resources).createCostString(spell);
  }

  private String getDurationValue(Spell spell) {
    if (spell.getDuration() == null) {
      return getUndefinedString();
    }
    return resources.getString("Spells.Duration." + spell.getDuration());
  }

  private String getSourceValue(Spell spell) {
  	if (spell.getSources().isEmpty()) {
  		return "";
  	}
    return new MagicSourceContributor<Spell>(resources).createSourceString(spell);
  }

  public String getUndefinedString() {
    return resources.getString("CardView.CharmConfiguration.Spells.Target.Undefined");
  }

  private String getDurationLabel() {
    return resources.getString("CardView.CharmConfiguration.Spells.Duration");
  }
  
  private String getKeywordsLabel() {
    return resources.getString("CardView.CharmConfiguration.Spells.Keywords");
  }

  private String getSourceLabel() {
    return resources.getString("CardView.CharmConfiguration.Spells.Source");
  }

  private String getCostLabel() {
    return resources.getString("CardView.CharmConfiguration.Spells.Cost");
  }
}
