package net.sf.anathema.hero.martial.persistence;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.individual.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.martial.model.MartialArtsModel;
import net.sf.anathema.hero.martial.model.StyleName;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.NullTraitStateMap;
import net.sf.anathema.hero.traits.persistence.TraitListPto;
import net.sf.anathema.hero.traits.persistence.TraitPersister;
import net.sf.anathema.hero.traits.persistence.TraitPto;
import net.sf.anathema.library.identifier.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class MartialArtsPersister extends AbstractModelJsonPersister<TraitListPto, MartialArtsModel> {

  private final TraitPersister traitPersister = new TraitPersister(new NullTraitStateMap());

  public MartialArtsPersister() {
    super("martialarts", TraitListPto.class);
  }

  @Override
  protected void loadModelFromPto(Hero hero, MartialArtsModel model, TraitListPto pto) {
    for (TraitPto traitPto : pto.traits) {
      model.selectStyle(new StyleName(traitPto.name));
      Trait style = model.getSelectedStyle();
      traitPersister.load(style, traitPto);
      model.learnSelectedStyle();
    }
  }

  @Override
  protected TraitListPto saveModelToPto(MartialArtsModel model) {
    TraitListPto traitListPto = new TraitListPto();
    for (Trait style : model.getLearnedStyles()) {
      TraitPto traitPto = new TraitPto();
      traitPersister.save(style, traitPto);
      traitListPto.traits.add(traitPto);
    }
    return traitListPto;
  }

  @Override
  public Identifier getModelId() {
    return MartialArtsModel.ID;
  }
}
