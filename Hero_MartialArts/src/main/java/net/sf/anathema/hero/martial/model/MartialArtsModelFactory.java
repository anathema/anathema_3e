package net.sf.anathema.hero.martial.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModelFactory;
import net.sf.anathema.hero.individual.model.SimpleModelTreeEntry;
import net.sf.anathema.hero.merits.model.MeritsModel;

@SuppressWarnings("UnusedDeclaration")
public class MartialArtsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public MartialArtsModelFactory() {
    super(MartialArtsModel.ID, AbilitiesModel.ID, CharmsModel.ID, MeritsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public MartialArtsModel create(TemplateFactory templateFactory, String templateId) {
    return new MartialArtsModelImpl();
  }
}