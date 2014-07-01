package net.sf.anathema.hero.combos.persistence;

import net.sf.anathema.hero.charms.persistence.CharmPto;

import java.util.ArrayList;
import java.util.List;

public class ComboPto {
  public String name;
  public String description;
  public List<CharmPto> charms = new ArrayList<>();
}
