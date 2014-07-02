package net.sf.anathema.charm.data;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.parser.template.CharmTemplate;

public class CharmData {

  private final CharmTemplate template;
  private final CharmName name;
  private final TreeReference treeReference;

  public CharmData(CharmTemplate template, CharmName name, TreeReference treeReference) {
    this.template = template;
    this.name = name;
    this.treeReference = treeReference;
  }

  public CharmName getName() {
    return name;
  }

  public TreeReference getTreeReference() {
    return treeReference;
  }
}
