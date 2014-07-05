package net.sf.anathema.hero.application.persistence;

import net.sf.anathema.hero.application.environment.HeroEnvironmentImpl;
import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.application.item.Item;
import net.sf.anathema.hero.dummy.DummyHeroType;
import net.sf.anathema.hero.dummy.DummyObjectFactory;
import net.sf.anathema.hero.dummy.SimpleDummyHeroSplat;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetProvider;
import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.message.Messaging;
import net.sf.anathema.platform.environment.Environment;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class HeroItemPersisterTest {
  private Messaging messaging = mock(Messaging.class);
  private SimpleDummyHeroSplat template = new SimpleDummyHeroSplat(new DummyHeroType(), null);

  @Test
  public void createsFullyLoadedCharacter() throws Exception {
    HeroEnvironment generics = createEnvironment();
    HeroItemPersister persister = new HeroItemPersister(generics, messaging);
    HeroItemData heroItemData = createNewCharacter(persister);
    assertThat(heroItemData.isFullyLoaded(), is(true));
  }

  private HeroEnvironment createEnvironment() {
    ExtensibleDataSetProvider dataSetProvider = mock(ExtensibleDataSetProvider.class);
    final DummyObjectFactory objectFactory = new DummyObjectFactory();
    Environment applicationEnvironment = Mockito.mock(Environment.class);
    Mockito.when(applicationEnvironment.getObjectFactory()).thenReturn(objectFactory);
    HeroEnvironment generics = new HeroEnvironmentImpl(null, applicationEnvironment, dataSetProvider);
    generics.getTemplateRegistry().register(template);
    return generics;
  }

  private HeroItemData createNewCharacter(HeroItemPersister persister) throws PersistenceException {
    Item item = persister.createNew(template);
    return (HeroItemData) item.getItemData();
  }
}
