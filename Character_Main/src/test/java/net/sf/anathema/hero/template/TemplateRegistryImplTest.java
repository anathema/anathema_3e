package net.sf.anathema.hero.template;

import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.hero.dummy.template.SimpleDummyCharacterSplat;
import net.sf.anathema.hero.environment.template.SplatTypeImpl;
import net.sf.anathema.hero.environment.template.TemplateRegistry;
import net.sf.anathema.hero.environment.template.TemplateRegistryImpl;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TemplateRegistryImplTest {

  private DummyMundaneCharacterType characterType = new DummyMundaneCharacterType();
  private TemplateRegistry registry;

  @Before
  public void setUp() throws Exception {
    this.registry = new TemplateRegistryImpl();
  }

  @Test
  public void testRegisterAndRetrieveTemplate() throws Exception {
    SimpleDummyCharacterSplat template = new SimpleDummyCharacterSplat(characterType, "Test");
    registry.register(template);
    assertEquals(template, registry.getTemplate(new SplatTypeImpl(characterType, new SimpleIdentifier("Test"))));
  }

  @Test
  public void testRegisterAndRetrieveAllSupportedTemplates() throws Exception {
    HeroSplat defaultTemplate = new SimpleDummyCharacterSplat(characterType, null);
    HeroSplat otherTemplate = new SimpleDummyCharacterSplat(characterType, "Second");
    registry.register(defaultTemplate);
    registry.register(otherTemplate);
    Collection<HeroSplat> allSupportedTemplates = registry.getAllSupportedTemplates(characterType);
    assertTrue(allSupportedTemplates.contains(defaultTemplate));
    assertTrue(allSupportedTemplates.contains(otherTemplate));
  }
}