package net.sf.anathema.hero.spells.compiler;

import net.sf.anathema.hero.application.environment.Inject;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.hero.environment.template.TemplateLoader;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.platform.dependencies.InterfaceFinder;

import java.util.List;

@net.sf.anathema.platform.initialization.ExtensibleDataSetCompiler
public class SpellCompiler implements ExtensibleDataSetCompiler {
  private static final String Spell_File_Recognition_Pattern = ".+?\\.spells";
  private final ListSpellCache cache = new ListSpellCache();
  private final SpellBuilder spellBuilder = new SpellBuilder();

  @Inject
  public InterfaceFinder finder;

  @Override
  public String getName() {
    return "Spells";
  }

  @Override
  public String getRecognitionPattern() {
    return Spell_File_Recognition_Pattern;
  }

  @Override
  public ExtensibleDataSet build() {
    return cache;
  }

  @Override
  public void registerFile(ResourceFile resource) {
    TemplateLoader<SpellListTemplate> loader = new GenericTemplateLoader<>(SpellListTemplate.class);
    SpellListTemplate template = loader.load(resource);
    List<Spell> spells = spellBuilder.buildSpells(template);
    spells.forEach(cache::addSpell);
  }
}