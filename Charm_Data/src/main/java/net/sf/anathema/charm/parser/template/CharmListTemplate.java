package net.sf.anathema.charm.parser.template;

import java.util.HashMap;
import java.util.Map;

public class CharmListTemplate {

  public String category;
  public String treeName;
  public Map<String, CharmTemplate> charmTemplatesByName = new HashMap<>();
}
