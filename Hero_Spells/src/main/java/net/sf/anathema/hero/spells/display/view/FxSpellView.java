package net.sf.anathema.hero.spells.display.view;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.charms.display.magic.FxMagicLearnView;
import net.sf.anathema.hero.charms.display.magic.MagicLearnProperties;
import net.sf.anathema.hero.charms.display.magic.MagicLearnView;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.hero.spells.display.presenter.SpellView;
import net.sf.anathema.hero.spells.display.presenter.SpellViewProperties;
import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.selection.ComboBoxSelectionView;
import net.sf.anathema.library.identifier.Identifier;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;

public class FxSpellView implements SpellView, NodeHolder {
  private final MigPane content = new MigPane(fillWithoutInsets());
  private final Announcer<ObjectChangedListener> circleControl = Announcer.to(ObjectChangedListener.class);
  private ComboBoxSelectionView<Identifier> selectionView;

  @Override
  public void addCircleSelection(Collection<Identifier> circles, SpellViewProperties properties) {
    this.selectionView = new ComboBoxSelectionView<>(properties.getCircleLabel(),
            properties.getCircleSelectionRenderer());
    selectionView.setObjects(circles);
    content.add(selectionView.getNode(), new CC().wrap());
    selectionView.addObjectSelectionChangedListener(new ObjectChangedListener<Identifier>() {
      @SuppressWarnings("unchecked")
      @Override
      public void valueChanged(Identifier newValue) {
        circleControl.announce().valueChanged(newValue);
      }
    });
  }

  @Override
  public void showSelectedCircle(CircleType newValue) {
    selectionView.setSelectedObject(newValue);
  }

  @Override
  public MagicLearnView addMagicLearnView(MagicLearnProperties properties) {
    FxMagicLearnView magicLearnView = new FxMagicLearnView(properties);
    content.add(magicLearnView.getNode(), new CC().grow().push());
    return magicLearnView;
  }

  @Override
  public void addCircleSelectionListener(ObjectChangedListener<CircleType> listener) {
    circleControl.addListener(listener);
  }

  @Override
  public Node getNode() {
    return content;
  }
}