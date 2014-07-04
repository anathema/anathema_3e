package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.template.ConfiguredModel;
import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.hero.template.TemplateType;
import net.sf.anathema.hero.template.TemplateTypeImpl;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;

public class SimpleDummyCharacterTemplate implements HeroTemplate {

  private final String subtype;
  private final CharacterType type;

  public SimpleDummyCharacterTemplate(CharacterType type, String subtype) {
    this.type = type;
    this.subtype = subtype;
  }

  @Override
  public TemplateType getTemplateType() {
    if (subtype == null) {
      return new TemplateTypeImpl(type, new SimpleIdentifier("Test"));
    }
    return new TemplateTypeImpl(type, new SimpleIdentifier(subtype));
  }

  @Override
  public List<ConfiguredModel> getModels() {
    return new ArrayList<>();
  }
}