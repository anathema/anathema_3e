package net.sf.anathema.hero.persistence;

import net.sf.anathema.framework.messaging.Messaging;
import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.application.item.Item;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;
import net.sf.anathema.hero.dummy.DummyObjectFactory;
import net.sf.anathema.hero.dummy.template.SimpleDummyCharacterTemplate;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentImpl;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.hero.framework.persistence.HeroItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class HeroItemPersisterTest {
  private Messaging messaging = mock(Messaging.class);
  private SimpleDummyCharacterTemplate template = new SimpleDummyCharacterTemplate(new DummyExaltCharacterType(), null);

  @Test
  public void createsFullyLoadedCharacter() throws Exception {
    HeroEnvironment generics = createEnvironment();
    HeroItemPersister persister = new HeroItemPersister(generics, messaging);
    HeroItemData heroItemData = createNewCharacter(persister);
    assertThat(heroItemData.isFullyLoaded(), is(true));
  }

  private HeroEnvironment createEnvironment() {
    IExtensibleDataSetProvider dataSetProvider = mock(IExtensibleDataSetProvider.class);
    final DummyObjectFactory objectFactory = new DummyObjectFactory();
    HeroEnvironment generics = new HeroEnvironmentImpl(null, objectFactory, dataSetProvider);
    generics.getTemplateRegistry().register(template);
    return generics;
  }

  private HeroItemData createNewCharacter(HeroItemPersister persister) throws PersistenceException {
    Item item = persister.createNew(template);
    return (HeroItemData) item.getItemData();
  }
}
