package net.sf.anathema.character.impl.view.magic;

import net.sf.anathema.character.presenter.magic.spells.SpellViewProperties;
import net.sf.anathema.character.view.magic.IComboConfigurationView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;

public class MagicViewFactory implements IMagicViewFactory {

  @Override
  public ICharmView createCharmSelectionView(final ISvgTreeViewProperties properties) {
    return new CharmView(properties);
  }

  @Override
  public IComboConfigurationView createCharmComboView() {
    return new ComboConfigurationView();
  }

  @Override
  public ISpellView createSpellView(final SpellViewProperties properties) {
    return new SpellView(properties);
  }
}
