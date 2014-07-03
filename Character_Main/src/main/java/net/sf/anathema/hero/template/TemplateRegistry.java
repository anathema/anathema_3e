package net.sf.anathema.hero.template;

import net.sf.anathema.hero.framework.type.CharacterType;

public interface TemplateRegistry {

  HeroTemplate[] getAllSupportedTemplates(CharacterType type);

  void register(HeroTemplate template);

  HeroTemplate getTemplate(TemplateType type);
}