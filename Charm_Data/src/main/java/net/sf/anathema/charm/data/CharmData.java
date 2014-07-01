package net.sf.anathema.charm.data;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.parser.template.CharmTemplate;

public class CharmData {

  private final CharmTemplate template;
  private final CharmName charmName;
  private final TreeReference treeReference;

  public CharmData(CharmTemplate template, CharmName charmName, TreeReference treeReference) {
    this.template = template;
    this.charmName = charmName;
    this.treeReference = treeReference;
  }

  public CharmName getCharmName() {
    return charmName;
  }

  public TreeReference getTreeReference() {
    return treeReference;
  }
}
