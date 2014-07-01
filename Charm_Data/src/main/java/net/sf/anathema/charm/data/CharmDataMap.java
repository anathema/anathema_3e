package net.sf.anathema.charm.data;

import net.sf.anathema.charm.data.reference.*;
import net.sf.anathema.charm.parser.template.CharmCollectionTemplate;
import net.sf.anathema.charm.parser.template.CharmTemplate;

import java.util.HashMap;
import java.util.Map;

public class CharmDataMap {

  private Map<CharmReference, CharmData> charmDataMap = new HashMap<>();

  public void addTemplates(CharmCollectionTemplate template) {
    for (Map.Entry<String,CharmTemplate> entry : template.charmTemplatesByName.entrySet()) {
      CharmReference charmReference = createReference(template, entry);
      CharmData charmData = new CharmData(entry.getValue(), charmReference);
      charmDataMap.put(charmReference, charmData);
    }
  }

  private CharmReference createReference(CharmCollectionTemplate template, Map.Entry<String,CharmTemplate> charmTemplateByName) {
    TreeCategoryReference typeName = new TreeCategoryReference(template.characterType);
    TreeName treeName = new TreeName(template.treeName);
    TreeReference treeReference = new TreeReference(typeName, treeName);
    CharmName charmName = new CharmName(charmTemplateByName.getKey());
    return new CharmReference(charmName, treeReference);
  }
}
