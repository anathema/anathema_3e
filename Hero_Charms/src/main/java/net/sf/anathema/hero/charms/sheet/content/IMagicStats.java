package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.stats.IStats;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.resources.Resources;

import java.util.Collection;

public interface IMagicStats extends IStats, Comparable<IMagicStats> {

  @Override
  Identifier getName();

  String getCostString(Resources resources);

  String getGroupName(Resources resources);

  String getType(Resources resources);

  String getDurationString(Resources resources);

  String getSourceString(Resources resources);

  Collection<String> getDetailStrings(Resources resources);

  String getNameString(Resources resources);
}