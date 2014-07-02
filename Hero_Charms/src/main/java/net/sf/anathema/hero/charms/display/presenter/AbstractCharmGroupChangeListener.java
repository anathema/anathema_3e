package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.hero.charms.display.node.CharmGraphNodeBuilder;
import net.sf.anathema.hero.charms.display.node.RenderingParents;
import net.sf.anathema.hero.charms.display.view.ICharmGroupChangeListener;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.TreeView;
import net.sf.anathema.platform.tree.document.GenericCascadeFactory;
import net.sf.anathema.platform.tree.document.visualizer.NodeDimensions;
import net.sf.anathema.platform.tree.document.visualizer.NodeDimensionsImpl;
import net.sf.anathema.platform.tree.view.AgnosticCascadeStrategy;
import net.sf.anathema.platform.tree.view.container.Cascade;

import java.util.*;
import java.util.stream.Collectors;

import static net.sf.anathema.hero.charms.display.node.RenderingParents.collectRenderingParents;

public abstract class AbstractCharmGroupChangeListener implements ICharmGroupChangeListener, CharmGroupInformer {

  private final CharmTreeArbitrator arbitrator;
  private CharmTree currentGroup;
  private Identifier currentType;
  private TreeView treeView;

  public AbstractCharmGroupChangeListener(CharmTreeArbitrator arbitrator) {
    this.arbitrator = arbitrator;
  }

  @Override
  public final void valueChanged(Object cascade, Object type) {
    loadCharmTree((CharmTree) cascade, (CategoryReference) type);
  }

  @Override
  public void operateOn(TreeView treeView) {
    this.treeView = treeView;
  }

  private void loadCharmTree(CharmTree charmGroup, CategoryReference category) {
    boolean resetView = !(currentGroup != null && currentGroup.equals(
            charmGroup) && currentType != null && currentType.equals(category));
    this.currentGroup = charmGroup;
    this.currentType = category;
    modifyCharmVisuals(category);
    if (charmGroup == null) {
      treeView.clear();
    } else {
      Set<Charm> charms = getDisplayCharms(charmGroup);
      IRegularNode[] nodesToShow = prepareNodes(charms);
      GenericCascadeFactory cascadeFactory = new GenericCascadeFactory(new AgnosticCascadeStrategy());
      NodeDimensions presentationProperties = new NodeDimensionsImpl();
      Cascade cascade = cascadeFactory.createCascade(nodesToShow, presentationProperties);
      treeView.loadCascade(cascade, resetView);
    }
  }

  private IRegularNode[] prepareNodes(Set<Charm> charms) {
    Collection<IIdentifiedRegularNode> nodes = CharmGraphNodeBuilder.createNodesFromCharms(charms);
    List<IIdentifiedRegularNode> sortedNodes = new ArrayList<>(nodes);
    Collections.sort(sortedNodes, (o1, o2) -> o1.getId().compareTo(o2.getId()));
    return sortedNodes.toArray(new IRegularNode[sortedNodes.size()]);
  }

  private Set<Charm> getDisplayCharms(CharmTree charmTree) {
    Set<Charm> charmsToDisplay = new LinkedHashSet<>();
    for (Charm charm : arbitrator.filterAvailableCharms(charmTree)) {
      charmsToDisplay.add(charm);
      charmsToDisplay.addAll(collectRenderingParents(charm).stream().filter(
              prerequisite -> charmTree.getReference().name.equals(prerequisite.getTreeReference().name)).collect(
              Collectors.toList()));
    }
    return charmsToDisplay;
  }

  @Override
  public CharmTree getCurrentTree() {
    return currentGroup;
  }

  protected abstract void modifyCharmVisuals(CategoryReference type);

  @Override
  public boolean hasGroupSelected() {
    return getCurrentTree() != null;
  }

  protected TreeView getTreeView() {
    return treeView;
  }
}