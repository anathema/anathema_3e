package net.sf.anathema.charm.template.evocations;

import net.sf.anathema.library.identifier.Identifier;

public enum EvocationTier implements Identifier, Comparable<EvocationTier> {
  None, Emerald, Sapphire, Adamant;

  @Override
  public String getId() {
    return toString();
  }
}