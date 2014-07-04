package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.application.perspective.CharacterItemCreationModel;
import net.sf.anathema.hero.dummy.DummyHeroTypes;
import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.hero.dummy.template.SimpleDummyCharacterSplat;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.template.TemplateRegistry;
import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.library.event.ChangeListener;
import org.junit.Test;
import org.mockito.Mockito;

import static java.util.Collections.singletonList;

public class CharacterItemCreationModelTest {

  @Test
  public void comparesNewlySetCharacterTypeViaEqualsNotIdentity() throws Exception {
    HeroEnvironment generics = createGenericsWithCharacterType(new DummyMundaneCharacterType());
    CharacterItemCreationModel model = new CharacterItemCreationModel(generics);
    ChangeListener listener = Mockito.mock(ChangeListener.class);
    model.addListener(listener);
    model.setCharacterType(new DummyMundaneCharacterType());
    Mockito.verifyZeroInteractions(listener);
  }

  private HeroEnvironment createGenericsWithCharacterType(CharacterType characterType) {
    HeroEnvironment generics = Mockito.mock(HeroEnvironment.class);
    DummyHeroTypes characterTypes = new DummyHeroTypes();
    characterTypes.add(characterType);
    Mockito.when(generics.getHeroTypes()).thenReturn(characterTypes);
    TemplateRegistry registry = Mockito.mock(TemplateRegistry.class);
    SimpleDummyCharacterSplat characterTemplate = new SimpleDummyCharacterSplat(characterType, null);
    Mockito.when(registry.getAllSupportedTemplates(characterType)).thenReturn(singletonList(characterTemplate));
    Mockito.when(generics.getTemplateRegistry()).thenReturn(registry);
    return generics;
  }
}
