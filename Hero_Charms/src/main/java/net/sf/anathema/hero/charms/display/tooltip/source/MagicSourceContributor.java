package net.sf.anathema.hero.charms.display.tooltip.source;

import net.sf.anathema.hero.magic.basic.Magic;
import net.sf.anathema.charm.old.source.SourceBook;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.display.tooltip.IMagicSourceStringBuilder;
import net.sf.anathema.hero.charms.display.tooltip.MagicTooltipContributor;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.lang.StringUtilities;

public class MagicSourceContributor<M extends Magic> implements IMagicSourceStringBuilder<M>, MagicTooltipContributor {

  private final Resources resources;

  public MagicSourceContributor(Resources resources) {
    this.resources = resources;
  }

  protected Resources getResources() {
    return resources;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object specialDetails) {
    String descriptionLabel = resources.getString("CharmTreeView.ToolTip.Source");
    String descriptionText = createSourceString((M) magic);
    tooltip.appendLine(descriptionLabel, descriptionText);
  }

  @Override
  public String createSourceString(M magic) {
    SourceBook[] sources = getSources(magic);
    String[] sourceStrings = new String[sources.length];
    for (int i = 0; i != sources.length; i++) {
      StringBuilder builder = new StringBuilder();
      builder.append(resources.getString(createSourceBookKey(sources[i])));
      String pageKey = createPageKey(magic.getName().text, sources[i]);
      if (resources.supportsKey(pageKey)) {
        builder.append(ConfigurableTooltip.CommaSpace);
        builder.append(resources.getString("CharmTreeView.ToolTip.Page"));
        builder.append(ConfigurableTooltip.Space);
        builder.append(resources.getString(pageKey));
      }
      sourceStrings[i] = builder.toString();
    }
    String andString = resources.getString("CharmTreeView.ToolTip.SourceAnd");
    return StringUtilities.joinStringsWithDelimiter(sourceStrings, ", " + andString + " ");
  }

  private String createSourceBookKey(SourceBook source) {
    return "ExaltedSourceBook." + source.getId();
  }

  private String createPageKey(String id, SourceBook source) {
    return source.getId() + "." + id + ".Page";
  }

  @Override
  public String createShortSourceString(M magic) {
    SourceBook[] sources = magic.getSources();
    if (sources == null || sources.length == 0) {
      return "";
    }
    String id = magic.getName().text;
    return createShortSourceString(sources[0], id);
  }

  protected SourceBook[] getSources(M magic) {
    return magic.getSources();
  }

  public String createShortSourceString(SourceBook source, String magicId) {
    StringBuilder builder = new StringBuilder();
    builder.append(resources.getString(createSourceBookKey(source) + ".Short"));
    String pageKey = createPageKey(magicId, source);
    if (resources.supportsKey(pageKey)) {
      builder.append(ConfigurableTooltip.CommaSpace);
      builder.append(resources.getString(pageKey));
    }
    return builder.toString();
  }
}
