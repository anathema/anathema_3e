package net.sf.anathema.charm.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.charm.template.prerequisite.CharmPrerequisiteTemplate;

public class CharmTemplate {

  public Map<String, Integer> minimums = new HashMap<>();
  public List<CharmPrerequisiteTemplate> prerequisites = new ArrayList<>();
  public String duration;
  public String cost;
  public List<String> keywords = new ArrayList<>();
  public List<String> internalTags = new ArrayList<>();
  public List<String> sources = new ArrayList<>();
}
