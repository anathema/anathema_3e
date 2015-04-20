package net.sf.anathema.hero.martial.model;

import net.sf.anathema.characterengine.support.Announcer;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.magic.data.reference.CategoryReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MartialArtsModelImpl implements MartialArtsModel {
  private final List<StyleName> availableStyleNames = new ArrayList<>();
  private final List<StyleName> learnedStyleNames = new ArrayList<>();
  private final Announcer<ChangeListener> selectionAnnouncer = Announcer.to(ChangeListener.class);
  private StyleName selectedStyle;

  @Override
  public Identifier getId() {
    return MartialArtsModel.ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    CharmsModel charmsModel = CharmsModelFetcher.fetch(hero);
    Collection<CharmTree> martialArtsTrees = charmsModel.getTreesFor(new CategoryReference("MartialArts"));
    for (CharmTree charmTree : martialArtsTrees) {
      String treeId = charmTree.getReference().name.text;
      availableStyleNames.add(new StyleName(treeId));
    }
    this.selectedStyle = availableStyleNames.get(0);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {

  }

  @Override
  public List<StyleName> getAllStyles() {
    return availableStyleNames;
  }

  @Override
  public void selectStyle(StyleName newValue) {
    if (newValue == selectedStyle) {
      return;
    }
    this.selectedStyle = newValue;
    selectionAnnouncer.announce().changeOccurred();
  }

  @Override
  public void whenStyleIsSelected(ChangeListener listener) {
    selectionAnnouncer.addListener(listener);
  }

  @Override
  public StyleName getSelectedStyle() {
    return selectedStyle;
  }

  @Override
  public void learnSelectedStyle() {
    learnedStyleNames.add(selectedStyle);
  }
}
