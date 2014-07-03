package net.sf.anathema.charm.template.special;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Upgradable {
  public boolean requiresBase = true;
  public List<String> upgrades = new ArrayList<>();
  public Map<String, Integer> bpCostsByName = new HashMap<>();
  public Map<String, Integer> xpCostsByName = new HashMap<>();
  public Map<String, Integer> essenceMinimumsByName = new HashMap<>();
  public Map<String, Integer> traitMinimumsByName = new HashMap<>();
  public Map<String, String> traitsByName = new HashMap<>();
}
