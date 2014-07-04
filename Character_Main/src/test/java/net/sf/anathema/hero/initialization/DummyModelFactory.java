package net.sf.anathema.hero.initialization;

import net.sf.anathema.hero.environment.template.TemplateFactory;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.initialization.DoNotInstantiateAutomatically;

import java.util.ArrayList;

@DoNotInstantiateAutomatically
public class DummyModelFactory implements HeroModelFactory {
  private final Identifier id;
  private final ArrayList<Identifier> requirements = new ArrayList<>();

  public DummyModelFactory(Identifier id) {
    this.id = id;
  }

  public void addRequirement(Identifier id) {
    requirements.add(id);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <M extends HeroModel> M create(TemplateFactory templateFactory, String templateId) {
    return (M) new DummyHeroModel(id);
  }

  @Override
  public Iterable<Identifier> getRequiredModelIds() {
    return requirements;
  }

  @Override
  public Identifier getModelId() {
    return id;
  }
}
