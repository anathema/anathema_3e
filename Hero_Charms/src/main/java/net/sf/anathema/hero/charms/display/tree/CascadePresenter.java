package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.coloring.CharmDye;
import net.sf.anathema.hero.charms.display.model.CategoryCollection;
import net.sf.anathema.hero.charms.display.special.NullSpecialCharmPresenter;
import net.sf.anathema.hero.charms.display.special.SpecialCharmViewPresenter;
import net.sf.anathema.hero.charms.display.view.*;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;
import net.sf.anathema.hero.magic.description.MagicDescriptionProvider;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.CascadeLoadedListener;
import net.sf.anathema.platform.tree.display.TreeView;
import net.sf.anathema.points.display.overview.presenter.SelectIdentifierConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CascadePresenter {

  private final Resources resources;
  private CharmTreeCollectionMap charmTreeCollectionMap;
  private CharmIdMap charmIdMap;
  private MagicDescriptionProvider magicDescriptionProvider;
  private ICharmGroupChangeListener changeListener;
  private CharmView view;
  private CharmDye dye;
  private CategoryCollection categoryCollection;
  protected CharmTreeCollection charmTrees;
  private SpecialCharmViewPresenter specialCharmPresenter = new NullSpecialCharmPresenter();
  private AlienCharmPresenter alienPresenter = new NullAlienCharmPresenter();
  private CharmInteractionPresenter interactionPresenter = new NullInteractionPresenter();
  private SpecialCharmSet specialCharmSet;

  public CascadePresenter(Resources resources, CharmIdMap charmIdMap,
                          MagicDescriptionProvider magicDescriptionProvider) {
    this.resources = resources;
    this.charmIdMap = charmIdMap;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public void initPresentation() {
    ObjectSelectionView<Identifier> typeSelector = createCharmTypeSelector();
    ObjectSelectionView<Identifier> groupSelector = createCharmGroupSelector();
    addTreeView();
    initListening(typeSelector, groupSelector);
    specialCharmPresenter.initPresentation();
    view.whenCursorLeavesCharmAreaResetAllPopups();
    alienPresenter.initPresentation(typeSelector);
    interactionPresenter.initPresentation();
  }

  private void addTreeView() {
    DefaultFunctionalNodeProperties functionalNodeProperties = new DefaultFunctionalNodeProperties();
    final TreeView treeView = view.addTreeView();
    treeView.loadNodeNamesFrom(new DefaultNodePresentationProperties(resources, functionalNodeProperties, charmIdMap));
    treeView.setCanvasBackground(RGBColor.White);
    treeView.initToolTips(
            new DefaultTooltipProperties(functionalNodeProperties, charmIdMap, resources, magicDescriptionProvider,
                    specialCharmSet));
    treeView.addCascadeLoadedListener(new CascadeLoadedListener() {
      @Override
      public void cascadeLoaded() {
        treeView.initNodeNames();
        dye.setCharmVisuals();
        specialCharmPresenter.showSpecialViews();
      }
    });
    changeListener.operateOn(treeView);
    dye.operateOn(treeView);
    interactionPresenter.operateOn(treeView);
    specialCharmPresenter.operateOn(treeView);
  }

  private void initListening(final ObjectSelectionView<Identifier> typeSelector,
                             final ObjectSelectionView<Identifier> groupSelector) {
    typeSelector.addObjectSelectionChangedListener(new ObjectValueListener<Identifier>() {
      @Override
      public void valueChanged(Identifier cascadeType) {
        handleTypeSelectionChange(cascadeType, groupSelector);
      }
    });
    groupSelector.addObjectSelectionChangedListener(new ObjectValueListener<Identifier>() {
      @Override
      public void valueChanged(Identifier newValue) {
        changeListener.valueChanged(newValue, typeSelector.getSelectedObject());
      }
    });
  }

  protected Resources getResources() {
    return resources;
  }

  private ObjectSelectionView<Identifier> createCharmTypeSelector() {
    List<CategoryReference> categories = categoryCollection.getCurrentCategories();
    String title = getResources().getString("CharmTreeView.GUI.CharmType");
    SelectIdentifierConfiguration<Identifier> config = new SelectIdentifierConfiguration<>(resources);
    ObjectSelectionView<Identifier> typeSelector = view.addSelectionView(title, config);
    typeSelector.setObjects(categories.toArray(new CategoryReference[categories.size()]));
    typeSelector.setSelectedObject(null);
    return typeSelector;
  }

  private ObjectSelectionView<Identifier> createCharmGroupSelector() {
    CharmTree[] allGroups = charmTrees.getAllCharmTrees();
    AgnosticUIConfiguration<Identifier> config = new SelectIdentifierConfiguration<>(resources);
    String title = getResources().getString("CardView.CharmConfiguration.AlienCharms.CharmGroup");
    ObjectSelectionView<Identifier> selector = view.addSelectionViewAndSizeItFor(title, config, allGroups);
    selector.setObjects(allGroups);
    selector.setSelectedObject(null);
    return selector;
  }

  private List<CharmTree> sortCharmGroups(CharmTree[] originalGroups) {
    ArrayList<CharmTree> filteredGroups = new ArrayList<>();
    Collections.addAll(filteredGroups, originalGroups);
    return new I18nedIdentificateSorter<CharmTree>().sortAscending(filteredGroups, resources);
  }

  protected void setSpecialPresenter(SpecialCharmViewPresenter presenter) {
    this.specialCharmPresenter = presenter;
  }

  public void setView(CharmView view) {
    this.view = view;
  }

  public void setChangeListener(ICharmGroupChangeListener charmGroupChangeListener) {
    this.changeListener = charmGroupChangeListener;
  }

  public void setCharmDye(CharmDye dye) {
    this.dye = dye;
  }

  public void setCategoryCollection(CategoryCollection types) {
    this.categoryCollection = types;
  }

  private void handleTypeSelectionChange(Identifier cascadeType, ObjectSelectionView<Identifier> groupSelector) {
    if (cascadeType == null) {
      groupSelector.setObjects(new Identifier[0]);
      return;
    }
    CharmTreeCollection charmTree = charmTreeCollectionMap.getCharmTree(cascadeType);
    if (charmTree == null) {
      groupSelector.setObjects(new Identifier[0]);
      return;
    }
    CharmTree[] allCharmGroups = charmTree.getAllCharmTrees();
    List<CharmTree> sortedGroups = sortCharmGroups(allCharmGroups);
    groupSelector.setObjects(sortedGroups.toArray(new CharmTree[sortedGroups.size()]));
    specialCharmPresenter.showSpecialViews();
  }

  protected void setAlienCharmPresenter(AlienCharmPresenter presenter) {
    this.alienPresenter = presenter;
  }

  protected void setInteractionPresenter(CharmInteractionPresenter presenter) {
    this.interactionPresenter = presenter;
  }

  public void setCharmTrees(CharmTreeCollection charmTrees) {
    this.charmTrees = charmTrees;
  }

  public void setSpecialCharmSet(SpecialCharmSet specialCharmSet) {
    this.specialCharmSet = specialCharmSet;
  }

  public void setCharmTreeCollectionMap(CharmTreeCollectionMap charmTreeCollectionMap) {
    this.charmTreeCollectionMap = charmTreeCollectionMap;
  }
}