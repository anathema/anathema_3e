package net.sf.anathema.charm.parser.template;

import java.util.HashMap;
import java.util.Map;

public class CharmCollectionTemplate {

  public String characterType;
  public String treeName;
  public Map<String, CharmTemplate> charmTemplatesByName = new HashMap<>();
}
