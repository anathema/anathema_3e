package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.character.main.magic.MartialArtsUtilities;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.lib.util.Identifier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MartialArtsUtilitiesTest {

  @Test
  public void testIsMartialArtsCharm() {
    ICharm charm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identifier attribute) {
        return attribute.getId().equals("MartialArts");
      }
    };
    assertTrue(MartialArtsUtilities.isMartialArtsCharm(charm));
  }

  @Test
  public void testIsFormCharm() throws Exception {
    ICharm charm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identifier attribute) {
        return attribute.getId().equals("Form");
      }
    };
    assertTrue(MartialArtsUtilities.isFormCharm(charm));
  }

  @Test
  public void testGetMartialArtsLevel() throws Exception {
    DummyCharm charm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identifier attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Terrestrial");
      }
    };
    assertEquals(MartialArtsLevel.Terrestrial, MartialArtsUtilities.getLevel(charm));
  }

  @Test
  public void testHasMartialArtsLevel() throws Exception {
    DummyCharm charm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identifier attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Terrestrial");
      }
    };
    assertTrue(MartialArtsUtilities.hasLevel(MartialArtsLevel.Terrestrial, charm));
  }
}