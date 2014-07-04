package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.framework.library.IStats;
import net.sf.anathema.lib.util.Identifier;

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