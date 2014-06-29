package net.sf.anathema.herotype.solar.display.curse;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.VirtueType;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.hero.advance.overview.presenter.SelectIdentifierConfiguration;
import net.sf.anathema.hero.display.configurableview.ConfigurableCharacterView;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.hero.traits.model.TraitChangeFlavor;
import net.sf.anathema.herotype.solar.model.curse.LimitBreak;
import net.sf.anathema.herotype.solar.model.curse.LimitBreakModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class VirtueFlawPresenter implements Presenter {

  private Hero hero;
  private final Resources resources;
  private final ConfigurableCharacterView view;
  private final LimitBreakModel model;

  public VirtueFlawPresenter(Hero hero, Resources resources, ConfigurableCharacterView virtueFlawView, LimitBreakModel model) {
    this.hero = hero;
    this.resources = resources;
    this.view = virtueFlawView;
    this.model = model;
  }

  @Override
  public void initPresentation() {
    initBasicPresentation();
    initAdditionalPresentation();
    initLimitPresentation(model.getLimitBreak());
  }

  protected void initAdditionalPresentation() {
    // Nothing to do
  }

  protected void initBasicPresentation() {
    LimitBreak limitBreak = model.getLimitBreak();
    initRootPresentation(limitBreak);
    initNamePresentation(limitBreak);
  }

  protected void initLimitPresentation(LimitBreak limitBreak) {
    Trait trait = limitBreak.getLimitTrait();
    IntValueView traitView =
            view.addDotSelector(getResources().getString(trait.getType().getId()), trait.getMaximalValue());
    new TraitPresenter(trait, traitView).initPresentation();
  }

  protected void initRootPresentation(final LimitBreak limitBreak) {
    final ObjectSelectionView<TraitType> rootView = view.addSelectionView(resources.getString("VirtueFlaw.Root.Name"), new VirtueTypeConfiguration());
    limitBreak.addRootChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        rootView.setSelectedObject(limitBreak.getRoot());
      }
    });
    rootView.addObjectSelectionChangedListener(new ObjectValueListener<TraitType>() {
      @Override
      public void valueChanged(TraitType newValue) {
        limitBreak.setRoot(newValue);
      }
    });
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (TraitChangeFlavor.changes(flavor, VirtueType.values())) {
          updateRootView(rootView);
        }
      }
    });
     updateRootView(rootView);
  }

  private void updateRootView(ObjectSelectionView<TraitType> rootView) {
    TraitType root = model.getLimitBreak().getRoot();
    rootView.setObjects(model.getFlawVirtueTypes());
    rootView.setSelectedObject(root);
  }

  protected ITextView initNamePresentation(LimitBreak limitBreak) {
    ITextView titleView = view.addLineView(resources.getString("VirtueFlaw.Name.Name"));
    new TextualPresentation().initView(titleView, limitBreak.getName());
    return titleView;
  }

  protected final Resources getResources() {
    return resources;
  }

  private class VirtueTypeConfiguration extends SelectIdentifierConfiguration<TraitType> {
    public VirtueTypeConfiguration() {
      super(VirtueFlawPresenter.this.resources);
    }

    @Override
    protected String getKeyForObject(TraitType value) {
      return "VirtueType.Name." + value.getId();
    }
  }
}