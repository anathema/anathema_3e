package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.hero.framework.display.labelledvalue.NullStyledValueView;
import net.sf.anathema.hero.spiritual.model.pool.EssencePoolModel;
import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import net.sf.anathema.library.presenter.Presenter;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.IntValueView;
import net.sf.anathema.library.view.StyledValueView;

public class EssenceConfigurationPresenter implements Presenter {

  private final SpiritualTraitsView view;
  private final EssencePoolModel essencePool;
  private final Resources resources;
  private final TraitMap traitMap;

  public EssenceConfigurationPresenter(Resources resources, EssencePoolModel essencePool, TraitMap traitMap, SpiritualTraitsView view) {
    this.resources = resources;
    this.essencePool = essencePool;
    this.traitMap = traitMap;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    Trait essenceTrait = traitMap.getTrait(OtherTraitType.Essence);
    IntValueView essenceView =
            view.addEssenceView(resources.getString("Essence.Name"), essenceTrait.getMaximalValue());
    if (essencePool.isEssenceUser()) {
      String key = "EssencePool.Name.Personal";
      String personalPool = essencePool.getPersonalPool();
      final StyledValueView<String> personalView = addPool(key, personalPool);
      final StyledValueView<String> peripheralView = createPeripheralPoolView();
      final StyledValueView<String> attunementView = addPool("EssencePool.Name.Attunement", essencePool.getAttunedPool());
      essencePool.addPoolChangeListener(() -> {
        personalView.setValue(essencePool.getPersonalPool());
        listenToPeripheralChanges(peripheralView);
        attunementView.setValue(essencePool.getAttunedPool());
      });
    }
    new TraitPresenter(essenceTrait, essenceView).initPresentation();
  }

  private void listenToPeripheralChanges(StyledValueView<String> peripheralView) {
    if (essencePool.hasPeripheralPool()) {
      peripheralView.setValue(essencePool.getPeripheralPool());
    }
  }

  private StyledValueView<String> createPeripheralPoolView() {
    if (essencePool.hasPeripheralPool()) {
      return addPool("EssencePool.Name.Peripheral", essencePool.getPeripheralPool());
    }
    return new NullStyledValueView<>();
  }

  private StyledValueView<String> addPool(String labelKey, String pool) {
    StyledValueView<String> valueView = view.addPoolView(resources.getString(labelKey));
    valueView.setValue(pool);
    return valueView;
  }
}
