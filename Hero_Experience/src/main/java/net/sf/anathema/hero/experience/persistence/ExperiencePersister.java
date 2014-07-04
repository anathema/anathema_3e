package net.sf.anathema.hero.experience.persistence;

import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.library.identifier.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class ExperiencePersister extends AbstractModelJsonPersister<ExperiencePto, ExperienceModel> {

  public ExperiencePersister() {
    super("experience", ExperiencePto.class);
  }

  @Override
  public Identifier getModelId() {
    return ExperienceModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, ExperienceModel model, ExperiencePto pto) {
    model.setExperienced(pto.isExperienced);
  }

  @Override
  protected ExperiencePto saveModelToPto(ExperienceModel model) {
    ExperiencePto pto = new ExperiencePto();
    pto.isExperienced = model.isExperienced();
    return pto;
  }
}
