package net.sf.anathema.charm.template;

import net.sf.anathema.charm.template.prerequisite.CharmPrerequisiteTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharmTemplate {

  public Map<String, Integer> minimums = new HashMap<>();
  public List<CharmPrerequisiteTemplate> prerequisites = new ArrayList<>();
  public String duration;
  public String cost;
  public List<String> keywords = new ArrayList<>();
  public List<String> internalTags = new ArrayList<>();
  public List<String> sources = new ArrayList<>();
  
  public CharmTemplate clone() {
  	CharmTemplate clone = new CharmTemplate();
  	clone.minimums.putAll(minimums);
  	clone.prerequisites.addAll(prerequisites);
  	clone.duration = duration;
  	clone.cost = cost;
  	clone.keywords.addAll(keywords);
  	clone.internalTags.addAll(internalTags);
  	clone.sources.addAll(sources);
  	return clone;
  }
}
