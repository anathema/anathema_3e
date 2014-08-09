package net.sf.anathema.magic.description.model;

import com.google.common.base.Strings;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.data.Magic;

import java.text.MessageFormat;

public class ShortMagicDescriptionProvider implements MagicDescriptionProvider {

  private Resources resources;

  public ShortMagicDescriptionProvider(Resources resources) {
    this.resources = resources;
  }

  @Override
  public MagicDescription getCharmDescription(final Magic magic) {
    return new MagicDescription() {
      @Override
      public boolean isEmpty() {
        return getParagraphs().isEmpty();
      }

      @Override
      public Paragraphs getParagraphs() {
        String descriptionString = getDescriptionString(magic);
        return Strings.isNullOrEmpty(descriptionString) ? new Paragraphs() : new Paragraphs(descriptionString);
      }

      private String getDescriptionString(Magic magic) {
        String id = magic.getName().text;
        String genericId = id; // todo (sandra) i18n-Key for Charms create and reuse here
        String description = getDescriptionPattern(id, genericId);
        if (magic instanceof Charm) {
          String traitId = ((Charm) magic).getPrerequisites().getPrimaryTraitType().type;
          description = MessageFormat.format(description, resources.getString(traitId));
        }
        return description;
      }

      private String getDescriptionPattern(String id, String genericId) {
        if (resources.supportsKey(id + ".Description")) {
          return resources.getString(id + ".Description");
        }
        if (resources.supportsKey(genericId + ".Description.Long")) {
          return resources.getString(genericId + ".Description.Long");
        }
        return "";
      }
    };
  }
}