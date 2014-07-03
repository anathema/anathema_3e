package net.sf.anathema.hero.magic.charm.martial;

import net.sf.anathema.charm.data.martial.MartialArtsLevel;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.magic.Magic;

import static net.sf.anathema.charm.data.CharmAttributeList.FORM_ATTRIBUTE;

public class MartialArtsUtilities {

  public static final SimpleIdentifier MARTIAL_ARTS = new SimpleIdentifier(AbilityType.MartialArts.name());

  public static boolean isMartialArts(Magic charm) {
    return charm.hasAttribute(MARTIAL_ARTS);
  }

  public static boolean isFormMagic(Magic charm) {
    return charm.hasAttribute(FORM_ATTRIBUTE);
  }

  // todo MartialArtsLevels ?
  public static MartialArtsLevel getLevel(Magic charm) {
    if (!isMartialArts(charm)) {
      return null;
    }
    return MartialArtsLevel.Celestial;
  }

  public static boolean hasLevel(MartialArtsLevel level, Charm charm) {
    return charm.hasAttribute(new SimpleIdentifier(level.name()));
  }

  public static CategoryReference getCategory(Identifier identifier) {
    return new CategoryReference(identifier.getId());
  }
}