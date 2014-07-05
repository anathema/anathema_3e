package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.dummy.DummyHeroTypes;
import net.sf.anathema.hero.dummy.DummyMundaneHeroType;
import net.sf.anathema.hero.dummy.SimpleDummyHeroSplat;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.template.TemplateRegistry;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.library.event.ChangeListener;
import org.junit.Test;
import org.mockito.Mockito;

import static java.util.Collections.singletonList;

public class CharacterItemCreationModelTest {

  @Test
  public void comparesNewlySetCharacterTypeViaEqualsNotIdentity() throws Exception {
    HeroEnvironment generics = createGenericsWithCharacterType(new DummyMundaneHeroType());
    CharacterItemCreationModel model = new CharacterItemCreationModel(generics);
    ChangeListener listener = Mockito.mock(ChangeListener.class);
    model.addListener(listener);
    model.setCharacterType(new DummyMundaneHeroType());
    Mockito.verifyZeroInteractions(listener);
  }

  private HeroEnvironment createGenericsWithCharacterType(HeroType heroType) {
    HeroEnvironment generics = Mockito.mock(HeroEnvironment.class);
    DummyHeroTypes characterTypes = new DummyHeroTypes();
    characterTypes.add(heroType);
    Mockito.when(generics.getHeroTypes()).thenReturn(characterTypes);
    TemplateRegistry registry = Mockito.mock(TemplateRegistry.class);
    SimpleDummyHeroSplat characterTemplate = new SimpleDummyHeroSplat(heroType, null);
    Mockito.when(registry.getAllSupportedTemplates(heroType)).thenReturn(singletonList(characterTemplate));
    Mockito.when(generics.getTemplateRegistry()).thenReturn(registry);
    return generics;
  }
}
