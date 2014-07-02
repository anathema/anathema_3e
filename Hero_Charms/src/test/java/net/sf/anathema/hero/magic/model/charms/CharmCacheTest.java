package net.sf.anathema.hero.magic.model.charms;

import com.google.common.collect.Lists;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;
import net.sf.anathema.hero.charms.compiler.special.ReflectionSpecialCharmBuilder;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.parser.dto.special.SpecialCharmDto;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharmCacheTest {

  private ReflectionSpecialCharmBuilder specialCharmBuilderMock = mock(ReflectionSpecialCharmBuilder.class);
  private CharmCacheImpl cache = new CharmCacheImpl(specialCharmBuilderMock);

  @Test
  // todo sandra: Fix or eliminate
  @Ignore
  public void matchesCharacterTypesToIdentificatesForSpecialCharmLookup() throws Exception {
    SpecialCharmDto specialCharmDto = mock(SpecialCharmDto.class);
    ISpecialCharm specialCharm = mock(ISpecialCharm.class);
    CategoryReference solar = new CategoryReference("Dummy");
    addSpecialCharmForSolar(specialCharmDto, solar);
    when(specialCharmBuilderMock.readCharm(specialCharmDto)).thenReturn(specialCharm);
    ISpecialCharm[] charmData = cache.getSpecialCharms(solar);
    assertThat(charmData[0], is(specialCharm));
  }

  @Test
  // todo sandra: Fix or eliminate
  @Ignore
  public void matchesCharacterTypesToIdentificatesForCharmLookup() throws Exception {
    CharmImpl charm = mock(CharmImpl.class);
    when(charm.getName()).thenReturn(new CharmName("Charm"));
    CategoryReference solar = new CategoryReference("Dummy");
    cache.addCharm(solar, charm);
    Charm[] charmData = cache.getCharms(solar);
    assertThat(charmData[0], is(charm));
  }

  private void addSpecialCharmForSolar(SpecialCharmDto specialCharm, CategoryReference solar) {
    ArrayList<SpecialCharmDto> data = Lists.newArrayList(specialCharm);
    cache.addSpecialCharmData(solar, data);
  }
}
