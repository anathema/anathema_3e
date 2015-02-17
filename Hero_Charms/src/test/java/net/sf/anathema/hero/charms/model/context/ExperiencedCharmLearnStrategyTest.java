package net.sf.anathema.hero.charms.model.context;

import net.sf.anathema.hero.charms.dummy.DummyCharm;
import net.sf.anathema.hero.charms.model.learn.BasicLearningModel;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExperiencedCharmLearnStrategyTest {

  DummyCharm charm = new DummyCharm("id");
  ExperiencedCharmLearnStrategy strategy = new ExperiencedCharmLearnStrategy();
  BasicLearningModel group = mock(BasicLearningModel.class);

  @Test
  public void charmsLearnedDuringCreationAreNotForgettable() throws Exception {
    configureLearnedDuringCreation();
    assertThat(strategy.isForgettable(group, charm), is(false));
  }

  @Test
  public void charmsLearnedWhileExperiencedAreForgettable() throws Exception {
    configureLearnedWithExperience();
    assertThat(strategy.isForgettable(group, charm), is(true));
  }

  private void configureLearnedDuringCreation() {
    when(group.isLearnedOnCreation(charm)).thenReturn(true);
    when(group.isCurrentlyLearned(charm)).thenReturn(true);
    when(group.isLearnedWithExperience(charm)).thenReturn(false);
  }

  private void configureLearnedWithExperience() {
    when(group.isLearnedOnCreation(charm)).thenReturn(false);
    when(group.isCurrentlyLearned(charm)).thenReturn(true);
    when(group.isLearnedWithExperience(charm)).thenReturn(true);
  }
}