package net.sf.anathema.charm.data;

import net.sf.anathema.charm.data.reference.MagicName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.parser.template.CharmTemplate;

public class CharmData {

  private final CharmTemplate template;
  private final MagicName magicName;
  private final TreeReference treeReference;

  public CharmData(CharmTemplate template, MagicName magicName, TreeReference treeReference) {
    this.template = template;
    this.magicName = magicName;
    this.treeReference = treeReference;
  }

  public MagicName getMagicName() {
    return magicName;
  }

  public TreeReference getTreeReference() {
    return treeReference;
  }
}
