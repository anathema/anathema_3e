package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.display.coloring.CharmDye;
import net.sf.anathema.hero.charms.display.model.CategoryCollection;
import net.sf.anathema.hero.charms.display.special.NullSpecialCharmPresenter;
import net.sf.anathema.hero.charms.display.special.SpecialCharmViewPresenter;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.display.view.DefaultFunctionalNodeProperties;
import net.sf.anathema.hero.charms.display.view.DefaultNodePresentationProperties;
import net.sf.anathema.hero.charms.display.view.DefaultTooltipProperties;
import net.sf.anathema.hero.charms.display.view.ICharmGroupChangeListener;
import net.sf.anathema.hero.charms.display.view.SpecialCharmSet;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.presenter.RGBColor;
import net.sf.anathema.library.presenter.SelectObjectConfiguration;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.sort.I18nedIdentificateSorter;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;
import net.sf.anathema.platform.tree.display.TreeView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CascadePresenter {

  private final Resources resources;
  private CharmTreeCollectionMap charmTreeCollectionMap;
  private CharmMap charmMap;
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

  public CascadePresenter(Resources resources, CharmMap charmMap,
                          MagicDescriptionProvider magicDescriptionProvider) {
    this.resources = resources;
    this.charmMap = charmMap;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public void initPresentation() {
    ObjectSelectionView<CategoryReference> typeSelector = createCategorySelector();
    ObjectSelectionView<CharmTree> groupSelector = createCharmTreeSelector();
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
    treeView.loadNodeNamesFrom(new DefaultNodePresentationProperties(resources, functionalNodeProperties, charmMap));
    treeView.setCanvasBackground(RGBColor.White);
    treeView.initToolTips(
            new DefaultTooltipProperties(functionalNodeProperties, charmMap, resources, magicDescriptionProvider,
                    specialCharmSet));
    treeView.addCascadeLoadedListener(() -> {
      treeView.initNodeNames();
      dye.setCharmVisuals();
      specialCharmPresenter.showSpecialViews();
    });
    changeListener.operateOn(treeView);
    dye.operateOn(treeView);
    interactionPresenter.operateOn(treeView);
    specialCharmPresenter.operateOn(treeView);
  }

  private void initListening(final ObjectSelectionView<CategoryReference> typeSelector,
                             final ObjectSelectionView<CharmTree> groupSelector) {
    typeSelector.addObjectSelectionChangedListener(cascadeType -> handleTypeSelectionChange(cascadeType, groupSelector));
    groupSelector.addObjectSelectionChangedListener(
            newValue -> changeListener.valueChanged(newValue, typeSelector.getSelectedObject()));
  }

  protected Resources getResources() {
    return resources;
  }

  private ObjectSelectionView<CategoryReference> createCategorySelector() {
    List<CategoryReference> categories = categoryCollection.getCurrentCategories();
    String title = getResources().getString("CharmTreeView.GUI.CharmType");
    SelectObjectConfiguration<CategoryReference> config = new SelectObjectConfiguration<>(resources,
            (i18nResources, category) -> i18nResources.getString(category.text));
    ObjectSelectionView<CategoryReference> typeSelector = view.addSelectionView(title, config);
    typeSelector.setObjects(categories.toArray(new CategoryReference[categories.size()]));
    typeSelector.setSelectedObject(null);
    return typeSelector;
  }

  private ObjectSelectionView<CharmTree> createCharmTreeSelector() {
    Collection<CharmTree> allGroups = charmTrees.getAllCharmTrees();
    AgnosticUIConfiguration<CharmTree> config = new SelectObjectConfiguration<>(resources,
            (i18nResources, tree) -> i18nResources.getString(tree.getReference().name.text));
    String title = getResources().getString("CardView.CharmConfiguration.AlienCharms.CharmGroup");
    ObjectSelectionView<CharmTree> selector = view.addSelectionViewAndSizeItFor(title, config, allGroups);
    selector.setObjects(allGroups);
    selector.setSelectedObject(null);
    return selector;
  }

  private List<CharmTree> sortCharmGroups(Collection<CharmTree> originalGroups) {
    ArrayList<CharmTree> filteredGroups = new ArrayList<>(originalGroups);
    return new I18nedIdentificateSorter<CharmTree>(resources).sortAscending(filteredGroups);
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

  private void handleTypeSelectionChange(CategoryReference cascadeType, ObjectSelectionView<CharmTree> groupSelector) {
    if (cascadeType == null) {
      groupSelector.setObjects(new CharmTree[0]);
      return;
    }
    CharmTreeCollection charmTree = charmTreeCollectionMap.getCharmTree(cascadeType);
    if (charmTree == null) {
      groupSelector.setObjects(new CharmTree[0]);
      return;
    }
    Collection<CharmTree> allCharmGroups = charmTree.getAllCharmTrees();
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