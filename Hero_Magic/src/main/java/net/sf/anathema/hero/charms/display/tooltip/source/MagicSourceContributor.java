package net.sf.anathema.hero.charms.display.tooltip.source;

import net.sf.anathema.hero.charms.display.tooltip.IMagicSourceStringBuilder;
import net.sf.anathema.hero.charms.display.tooltip.MagicTooltipContributor;
import net.sf.anathema.library.lang.StringUtilities;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.tooltip.ConfigurableTooltip;
import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.magic.data.source.SourceBook;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
    List<SourceBook> sources = getSources(magic);
    List<String> sourceStrings = sources.stream().map(source -> {
      StringBuilder builder = new StringBuilder();
      builder.append(resources.getString(createSourceBookKey(source)));
      String pageKey = createPageKey(magic.getName().text, source);
      if (resources.supportsKey(pageKey)) {
        builder.append(ConfigurableTooltip.CommaSpace);
        builder.append(resources.getString("CharmTreeView.ToolTip.Page"));
        builder.append(ConfigurableTooltip.Space);
        builder.append(resources.getString(pageKey));
      }
      return builder.toString();
    }).collect(toList());
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
    List<SourceBook> sources = magic.getSources();
    if (sources.isEmpty()) {
      return "";
    }
    String id = magic.getName().text;
    return createShortSourceString(sources.get(0), id);
  }

  protected List<SourceBook> getSources(M magic) {
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
