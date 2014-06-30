package net.sf.anathema.charm.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharmCollectionTemplate {

  public String characterType;
  public String treeName;
  public Map<String, CharmTemplate> charmTemplatesByName = new HashMap<>();
}
