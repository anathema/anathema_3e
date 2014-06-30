package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.hero.charms.model.learn.LearningCharmTree;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.charm.old.attribute.CharmAttributeList;
import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.hero.charms.model.learn.MartialArtsLearnModelImpl;
import net.sf.anathema.hero.magic.dummy.DummyCharmsModel;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class DefaultMartialArtsCharmConfiguration_Test {

  @Test
  public void testAlternativesDontBlockCompletion() throws Exception {
    LearningCharmTree group = Mockito.mock(LearningCharmTree.class);
    expectCoreCharmsCall(group);
    expectCoreCharmsCall(group);
    DummyCharmsModel dummyConfig = new DummyCharmsModel();
    dummyConfig.setGroups(group);
    ExperienceModel experienceModel = new ExperienceModel() {
      public boolean experienced;

      @Override
      public boolean isExperienced() {
        return experienced;
      }

      @Override
      public void setExperienced(boolean experienced) {
        this.experienced = experienced;
      }

      @Override
      public void addStateChangeListener(ChangeListener listener) {
        // nothing to do
      }

      @Override
      public Identifier getId() {
        return ID;
      }

      @Override
      public void initialize(HeroEnvironment environment, Hero hero) {
        // nothing to do
      }

      @Override
      public void initializeListening(ChangeAnnouncer announcer) {
        // nothing to do
      }
    };
    MartialArtsLearnModelImpl configuration = new MartialArtsLearnModelImpl(dummyConfig, experienceModel);
    boolean celestialMartialArtsGroupCompleted = configuration.isAnyCelestialStyleCompleted();
    Assert.assertTrue(celestialMartialArtsGroupCompleted);
  }

  private void expectCoreCharmsCall(LearningCharmTree group) {
    Mockito.when(group.getCoreCharms()).thenReturn(new Charm[]{new DummyCharm() {
      @Override
      public boolean isBlockedByAlternative(ICharmLearnArbitrator learnArbitrator) {
        return true;
      }

      @Override
      public boolean hasAttribute(Identifier attribute) {
        return !attribute.equals(CharmAttributeList.NO_STYLE_ATTRIBUTE);
      }
    }});
  }
}