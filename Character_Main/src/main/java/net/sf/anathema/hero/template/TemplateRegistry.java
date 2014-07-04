package net.sf.anathema.hero.template;

import net.sf.anathema.hero.framework.type.CharacterType;

import java.util.Collection;

public interface TemplateRegistry {

  Collection<HeroTemplate> getAllSupportedTemplates(CharacterType type);

  void register(HeroTemplate template);

  HeroTemplate getTemplate(TemplateType type);
}